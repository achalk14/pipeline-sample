package com.example.demospring.api;

import com.example.demospring.model.Person;
import com.example.demospring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    //public void addPerson(@Valid @NotNull @RequestBody Person person) {
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.selectEveryone();
    }

    @GetMapping(path = "/{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path = "/{id}")
    // public int updatePersonById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person updatedPerson) {
    public int updatePersonById(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person updatedPerson) {
        return personService.updatePerson(id, updatedPerson);
    }
}
