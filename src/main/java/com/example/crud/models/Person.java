package com.example.crud.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Person {

    @GeneratedValue
    @Id
    private UUID id;

    private String name;

    private int age;

    public Person() {}
}
