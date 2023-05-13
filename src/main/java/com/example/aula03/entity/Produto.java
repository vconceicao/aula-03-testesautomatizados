package com.example.aula03.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@Data
@ToString
@Entity
public class Produto {


    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String descricao;
    private Long unidades;


    public Produto(long id, String nome, String descricao, long unidades) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.unidades = unidades;
    }
}
