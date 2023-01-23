package ru.VYurkin.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int book_id;

    private int person_id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть не короче 2 символов, но и не длиннее 100 символов")
    private String name_book;

    @NotEmpty(message = "Поле автор не должно быть пустым")
    @Size(min = 2, max = 100, message = "Поле автор должно быть заполнено текстом не короче 2 символов, но и не длиннее 100 символов")
    private String name_author;

    @Min(value = 1600, message = "Год издания должен быть больше 1600")
    private int year_written;

    public Book() {
    }

    public Book(int book_id, String name_book, String name_author, int year_written) {
        this.book_id = book_id;
        this.name_book = name_book;
        this.name_author = name_author;
        this.year_written = year_written;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }

    public int getYear_written() {
        return year_written;
    }

    public void setYear_written(int year_written) {
        this.year_written = year_written;
    }
}
