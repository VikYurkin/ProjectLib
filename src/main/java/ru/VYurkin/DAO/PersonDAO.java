package ru.VYurkin.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.VYurkin.models.Person;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> indexPerson() {
        return jdbcTemplate.query("SELECT *FROM Person", new BeanPropertyRowMapper<>(Person.class));}

    public Person showPerson(Integer person_id) {
        return jdbcTemplate.query("SELECT*FROM Person WHERE person_id=?", new Object[]{person_id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);}

    public Optional<Person> showPerson(String name) {
        return jdbcTemplate.query("SELECT*FROM Person WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();}


    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, year_birth) VALUES (?, ?)", person.getName(), person.getYear_birth());}

    public void updatePerson(int person_id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, year_birth=? WHERE person_id=?", updatedPerson.getName(), updatedPerson.getYear_birth(), person_id);}

    public void deletePerson(int person_id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", person_id);
    }

}
