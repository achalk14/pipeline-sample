package com.example.demospring.dao;

import com.example.demospring.model.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository("postgres")
public class PersonDaoService implements PersonDao{
    private final JdbcTemplate jdbcTemplate;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    public PersonDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, @NotNull Person person) {
        try {
            String sql = "INSERT INTO PERSON (id, name) VALUES (?, ?)";
            Object[] parameters = { id, person.getName() };
            jdbcTemplate.update(sql, parameters);
            return 1;
        }
        catch (DataAccessException ex) {
            LOGGER.log(Level.SEVERE, "Insert into person table failed! Try again!");
            return 0;
        }
    }

    @Override
    public List<Person> selectAllPeople() {
        String sql = "SELECT id, name FROM person";
        List<Person> people = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
        return people;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        String sql = "SELECT id, name FROM person WHERE id = ?";

        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{ id },
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    System.out.println(personId);
                    System.out.println(name);
                    return new Person(personId, name);
                });

        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        try {
            String sql = "DELETE FROM PERSON WHERE id = ?";
            Object[] parameters = { id };
            jdbcTemplate.update(sql, parameters);
            return 1;
        }
        catch (DataAccessException ex) {
            LOGGER.log(Level.SEVERE, "Delete for person table with id "+ id +" failed! Try again!");
            return 0;
        }
    }

    @Override
    public int modifyPersonById(UUID id, Person person) {
        try {
            String sql = "UPDATE person SET name = ? WHERE id = ?;";
            Object[] parameters = { person.getName(), id };
            jdbcTemplate.update(sql, parameters);
            return 1;
        }
        catch (DataAccessException ex) {
            LOGGER.log(Level.SEVERE, "Update for person table with id "+ id +" failed! Try again!");
            return 0;
        }
    }
}
