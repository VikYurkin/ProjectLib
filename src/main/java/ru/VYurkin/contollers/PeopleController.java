package ru.VYurkin.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.VYurkin.config.DAO.BookDAO;
import ru.VYurkin.config.DAO.PersonDAO;
import ru.VYurkin.models.Book;
import ru.VYurkin.models.Person;
//import ru.VYurkin.util.PersonValidator;
import javax.validation.Valid;

@Controller
//@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    private final BookDAO bookDAO;
   // private final PersonValidator personValidator;

//    @Autowired
//    public PeopleController(PersonDAO personDAO, PersonValidator personValidator
//    ) {
//        this.personDAO = personDAO;
//        this.personValidator = personValidator;
//    }

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }




    @GetMapping("/people")
    public String indexPeople(Model model){
        model.addAttribute("people", personDAO.indexPerson());
        return "people/indexPeople";
    }

    @GetMapping("/books")
    public String indexBooks(Model model){
        model.addAttribute("books", bookDAO.indexBook());
        return "books/indexBooks";
    }





    @GetMapping("/people/{person_id}")
    public String showPerson(@PathVariable("person_id") int person_id, Model model){
        model.addAttribute("person", personDAO.showPerson(person_id));
        model.addAttribute("books", bookDAO.listBookFromPerson(person_id));
        return "people/showPerson";
    }
    @GetMapping("/books/{book_id}")
    public String showBook(@PathVariable("book_id") int book_id, Model model){
        model.addAttribute("book", bookDAO.showBook(book_id));
        model.addAttribute("person", personDAO.showPerson(bookDAO.showBook(book_id).getPerson_id()));
        return "books/showBook";
    }




    @GetMapping("/people/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping("/people")
    public String createPerson(@ModelAttribute("person") @Valid Person person
           // , BindingResult bindingResult
    ){
     //   personValidator.validate(person, bindingResult);

       // if(bindingResult.hasErrors())
       //     return "people/new";
        personDAO.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/books/new")
    public String newBooks(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping("/books")
    public String createBook(@ModelAttribute("book") @Valid Book book
           // , BindingResult bindingResult
    ){
        //   personValidator.validate(person, bindingResult);

       // if(bindingResult.hasErrors())
       //     return "people/new";

        bookDAO.saveBook(book);
        return "redirect:/books";
    }






    @GetMapping("/people/{person_id}/edit")
    public String editPerson(Model model,@PathVariable("person_id") int person_id){
        model.addAttribute("person",personDAO.showPerson(person_id));
        return "people/edit";
    }

    @PatchMapping("/people/{person_id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person
            //, BindingResult bindingResult
            , @PathVariable("person_id") int person_id){
       // personValidator.validate(person, bindingResult);
       // if(bindingResult.hasErrors())
        //    return "people/edit";

        personDAO.updatePerson(person_id,person);
        return "redirect:/people";
    }

    @GetMapping("/books/{book_id}/edit")
    public String editBook(Model model,@PathVariable("book_id") int book_id){
        model.addAttribute("book",bookDAO.showBook(book_id));
        return "books/edit";
    }

    @PatchMapping("/books/{book_id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book
                               //, BindingResult bindingResult
            , @PathVariable("book_id") int book_id){
        // personValidator.validate(person, bindingResult);
        // if(bindingResult.hasErrors())
        //    return "people/edit";

        bookDAO.updateBook(book_id, book);
        return "redirect:/books";
    }






    @DeleteMapping("/people/{person_id}")
    public String deletePerson(@PathVariable("person_id") int person_id){
        personDAO.deletePerson(person_id);
        return "redirect:/people";
    }
    @DeleteMapping("/book/{book_id}")
    public String deleteBook(@PathVariable("book_id") int book_id){
        bookDAO.deleteBook(book_id);
        return "redirect:/books";
    }









}
