package ru.VYurkin.config.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.VYurkin.models.Book;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {


    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();

        book.setBook_id(resultSet.getInt("book_id"));
        book.setPerson_id(resultSet.getInt("person_id"));
        book.setName_book(resultSet.getString("name_book"));
        book.setName_author(resultSet.getString("name_author"));
        book.setYear_written(resultSet.getInt("year_written"));

        return book;
    }
}
