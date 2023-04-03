package com.example.crud.resources;

import com.example.crud.models.Pessoa;
import com.example.crud.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    public ResponseEntity<Pessoa>save(@RequestBody Pessoa person){
        pessoaRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll(){
        List<Pessoa> persons = new ArrayList<>();
        persons = pessoaRepository.findAll();
        return new ResponseEntity<>(persons,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> getById(@PathVariable UUID id) {
        Optional<Pessoa> person;
        try {
            person = pessoaRepository.findById(id);
            return new ResponseEntity<Optional<Pessoa>>(person,HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> deleteByID(@PathVariable UUID id){
        try {
            pessoaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable UUID id, @RequestBody Pessoa newperson) {
        return pessoaRepository.findById(id)
                .map(person -> {
                    person.setNome(newperson.getNome());
                    person.setIdade(newperson.getIdade());
                    person.setSexo(newperson.getSexo());
                    person.setEmail(newperson.getEmail());
                    person.setDataDeNascimento(newperson.getDataDeNascimento());
                    person.setNacionalidade(newperson.getNacionalidade());
                    person.setCpf(newperson.getCpf());
                    Pessoa personUpdated = pessoaRepository.save(person);
                    return ResponseEntity.ok().body(personUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
