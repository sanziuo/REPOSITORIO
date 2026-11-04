package br.com.suaempresa.restaurante;

import jakarta.persistence.*;

// @Entity: o Hibernate transforma esta classe na tabela PRODUTO
@Entity
public class Produto {

    // @Id: chave primária
    // @GeneratedValue(IDENTITY): auto incremento feito pelo banco (H2/MySQL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;    // vira a coluna NOME
    private Double preco;   // vira a coluna PRECO

    // Construtor vazio OBRIGATÓRIO: o Hibernate cria o objeto com ele
    // e depois preenche os atributos pelos setters
    public Produto() {
    }

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters: Alt+Insert → Getter and Setter → selecione todos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

}