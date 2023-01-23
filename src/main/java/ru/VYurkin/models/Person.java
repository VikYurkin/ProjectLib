package ru.VYurkin.models;

import javax.validation.constraints.*;

public class Person {

    private int person_id;

    @NotEmpty(message = "ФИО не должны быть пустыми")
    @Size(min = 2, max = 100, message = "ФИО должны быть не короче 2 символов, и не длиннее 100 символов")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int year_birth;

    public Person() {
    }

    public Person(int person_id, String name, int year_birth) {
        this.person_id = person_id;
        this.name = name;
        this.year_birth = year_birth;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_birth() {
        return year_birth;
    }

    public void setYear_birth(int year_birth) {
        this.year_birth = year_birth;
    }
}


