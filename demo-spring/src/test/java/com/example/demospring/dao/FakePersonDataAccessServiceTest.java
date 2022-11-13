package com.example.demospring.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.example.demospring.model.Person;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FakePersonDataAccessServiceTest {
    @Autowired
    private FakePersonDataAccessService fkDaoTest;

    @Before
    public void setUp() {
        this.fkDaoTest = new FakePersonDataAccessService();
//        fkDaoTest = MBeanFactory
    }

    @Test
    // @Transactional
    public void testCRUD() {
        UUID idOne = UUID.randomUUID();
        Person personOne = new Person(idOne, "Achal Kumar");

        UUID idTwo = UUID.randomUUID();
        Person personTwo = new Person(idTwo, "Kavita Pant");

        fkDaoTest.insertPerson(idOne, personOne);
        fkDaoTest.insertPerson(idTwo, personTwo);

        assertThat(fkDaoTest.selectPersonById(idOne))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat("Achal Kumar"));

        assertThat(fkDaoTest.selectPersonById(idTwo))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat("Kavita Pant"));

        List<Person> people = fkDaoTest.selectAllPeople();

        int count = people.size();
        assertEquals(2, count);

        assertThat(people, contains(
                hasProperty("name", is("Achal Kumar")),
                hasProperty("name", is("Kavita Pant"))
        ));
    }
}
