package com.dobackaofront.model; // pacote das entidades (tabelas)

import jakarta.persistence.Entity;          // anotação de entidade
import jakarta.persistence.GeneratedValue;  // geração automática do id
import jakarta.persistence.GenerationType;  // tipos de geração do id
import jakarta.persistence.Id;              // anotação de chave primária

// Entidade: esta classe vira a tabela PRODUTO no banco
@Entity
public class Produto {

    @Id                                                  // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // id gerado pelo banco (auto incremento)
    private Long id;                                     // coluna ID

    private String nome;      // coluna NOME
    private Double preco;     // coluna PRECO
    private String categoria; // coluna CATEGORIA (exercício Cap. 3)
    private String descricao; // coluna DESCRICAO (exercício Cap. 5)

    // Construtor vazio: obrigatório para o Hibernate
    public Produto() {
    }

    // Getters (leem) e Setters (alteram)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}