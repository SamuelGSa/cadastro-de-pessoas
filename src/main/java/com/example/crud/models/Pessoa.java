package com.example.crud.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

import static com.example.crud.utils.DataUtils.getDataSistemaToString;

@Data
@Table(name = "pessoa")
@Entity
public class Pessoa {

    @GeneratedValue
    @Id
    private UUID id;

    private String nome;
    private int idade;
    private SexoEnum sexo;
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String dataDeNascimento;

    private String nacionalidade;
    private String cpf;


    private String dataDeCadastro = getDataSistemaToString();

    public Pessoa() {}

}
