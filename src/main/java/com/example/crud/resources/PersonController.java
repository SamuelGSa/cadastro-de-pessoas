package com.example.crud.resources;

import com.example.crud.models.Person;
import com.example.crud.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository){
        super();
        this.personRepository = personRepository;
    }

    @PostMapping
    public ResponseEntity<Person>save(@RequestBody Person person){
        personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll(){
        List<Person> persons = new ArrayList<>();
        persons = personRepository.findAll();
        return new ResponseEntity<>(persons,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> getById(@PathVariable Integer id) {
        Optional<Person> person;
        try {
            person = personRepository.findById(id);
            return new ResponseEntity<Optional<Person>>(person,HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Person>> deleteByID(@PathVariable Integer id){
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person newperson) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setName(newperson.getName());
                    person.setAge(newperson.getAge());
                    Person personUpdated = personRepository.save(person);
                    return ResponseEntity.ok().body(personUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
