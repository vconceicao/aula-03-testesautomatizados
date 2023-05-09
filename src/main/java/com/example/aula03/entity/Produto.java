package com.example.aula03.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@RequiredArgsConstructor
@Data
@ToString
@Entity
public class Produto {
    @jakarta.persistence.Id
    @Column(name = "id_produto", nullable = false)
    private Long idProduto;

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String descricao;
    private Long unidades;

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Produto(long id, String nome, String descricao, long unidades) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.unidades = unidades;
    }
}
