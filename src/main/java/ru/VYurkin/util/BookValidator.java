package ru.VYurkin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.VYurkin.config.DAO.BookDAO;
import ru.VYurkin.models.Book;

import java.util.List;


@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        List<Book> list = bookDAO.showBooks(book.getName_author());
        for(Book bookInList:list){
            System.out.println(book.getName_book().equals(bookInList.getName_book()));
            if(book.getName_book().equals(bookInList.getName_book())){
                errors.rejectValue("name_book", "", "Эта книга уже зарегистрирована");
            }
        }
    }
}
