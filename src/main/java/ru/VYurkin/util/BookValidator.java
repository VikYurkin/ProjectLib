package ru.VYurkin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.VYurkin.config.DAO.BookDAO;
import ru.VYurkin.config.DAO.PersonDAO;
import ru.VYurkin.models.Book;
import ru.VYurkin.models.Person;

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
        if ((bookDAO.showBook(book.getBook_id()) == null) & (bookDAO.showBook(book.getBook_id()).getPerson_id() == 0)) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
