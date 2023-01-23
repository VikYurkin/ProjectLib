package ru.VYurkin.config.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.VYurkin.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setPerson_id(resultSet.getInt("person_id"));
        person.setName(resultSet.getString("name"));
        person.setYear_birth(resultSet.getInt("year_birth"));

        return person;
    }
}
