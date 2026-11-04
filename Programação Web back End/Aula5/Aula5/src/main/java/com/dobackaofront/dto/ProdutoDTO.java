package com.dobackaofront.dto; // pacote dos DTOs

// DTO: dados que trafegam entre a tela/API e o Controller (não é tabela)
public class ProdutoDTO {

    private Long id;          // código
    private String nome;      // nome
    private Double preco;     // preço
    private String categoria; // categoria
    private String descricao; // descrição (exercício Cap. 5)

    // Construtor vazio: o formulário precisa criar um ProdutoDTO em branco
    public ProdutoDTO() {
    }

    // Getters e Setters: usados pelo th:field do formulário e pela conversão JSON
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