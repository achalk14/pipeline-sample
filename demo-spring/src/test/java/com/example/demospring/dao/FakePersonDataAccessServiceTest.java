package com.example.demospring.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.example.demospring.model.Person;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class FakePersonDataAccessServiceTest {
    @Autowired
    private FakePersonDataAccessService fkDaoTest;

    @Before
    public void setUp() {
        this.fkDaoTest = new FakePersonDataAccessService();
    }

    @Test
    public void testInsert() {
        // Insert person into database
        UUID idOne = UUID.randomUUID();
        Person personOne = new Person(idOne, "Achal Kumar");

        UUID idTwo = UUID.randomUUID();
        Person personTwo = new Person(idTwo, "Kavita Pant");

        fkDaoTest.insertPerson(idOne, personOne);
        fkDaoTest.insertPerson(idTwo, personTwo);
        
        // Test insert
        assertThat(fkDaoTest.selectPersonById(idOne))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat("Achal Kumar"));

        assertThat(fkDaoTest.selectPersonById(idTwo))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat("Kavita Pant"));
    }

    @Test
    public void testRetrive() {
        UUID idOne = UUID.randomUUID();
        Person personOne = new Person(idOne, "Achal Kumar");

        UUID idTwo = UUID.randomUUID();
        Person personTwo = new Person(idTwo, "Kavita Pant");
        
        // // Insert both people into the database
        fkDaoTest.insertPerson(idOne, personOne);
        fkDaoTest.insertPerson(idTwo, personTwo);

        List<Person> people = fkDaoTest.selectAllPeople();

        // Test all the persons in the database
        assertEquals(2, people.size());
        assertThat(people, contains(
                hasProperty("name", is("Achal Kumar")),
                hasProperty("name", is("Kavita Pant"))
        ));
    }

    @Test
    public void testUpdate() {
        // Create a person with idOne
        UUID idOne = UUID.randomUUID();
        Person personOne = new Person(idOne, "John Doe");
        fkDaoTest.insertPerson(idOne, personOne);

        // Test if the person exists
        assertThat(fkDaoTest.selectPersonById(idOne))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat("John Doe"));

        // Modify the person with idOne
        Person updatedPersonOne = new Person(idOne, "Kaytee P");
        fkDaoTest.modifyPersonById(idOne, updatedPersonOne);
        
        // Test if the person with idOne is modified
        assertThat(fkDaoTest.selectPersonById(idOne))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat("Kaytee P"));
    }

    @Test
    public void testDelete() {
        // Create a person with idOne
        UUID idOne = UUID.randomUUID();
        Person personOne = new Person(idOne, "Achal Kumar");
        
        // Insert the person with idOne
        fkDaoTest.insertPerson(idOne, personOne);
        
        // Delete person with idOne
        fkDaoTest.deletePersonById(idOne);

        // Test if person with idOne is deleted
        assertThat(fkDaoTest.selectPersonById(idOne)).isEmpty();
    }
}
