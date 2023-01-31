package ru.VYurkin.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.VYurkin.models.Book;
import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> indexBook() {
        return jdbcTemplate.query("SELECT *FROM Book", new BeanPropertyRowMapper<>(Book.class));}

    public Book showBook(int book_id) {
        return jdbcTemplate.query("SELECT*FROM Book WHERE book_id=?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);}

    public List<Book> showBooks(String name_author) {
        return jdbcTemplate.query("SELECT*FROM Book WHERE name_author=?", new Object[]{name_author}, new BeanPropertyRowMapper<>(Book.class));}

    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name_book, name_author, year_written) VALUES ( ?, ?, ? )", book.getName_book(), book.getName_author(), book.getYear_written());}

    public void updateBook(int book_id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name_book=?, name_author=?, year_written=? WHERE book_id=?", updatedBook.getName_book(), updatedBook.getName_author(),updatedBook.getYear_written(), book_id);}

    public void deleteBook(int book_id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", book_id);
    }

    public List<Book> listBookFromPerson(int person_id){
        return jdbcTemplate.query("SELECT*FROM Book WHERE person_id=?", new Object[]{person_id}, new BeanPropertyRowMapper<>(Book.class));}

    public void personGetBook (int book_id, int person_id){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person_id, book_id);}
    public void personReturnBook(int book_id){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?",null, book_id);}

}
