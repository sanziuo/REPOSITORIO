package com.dobackaofront.dto; // pacote dos DTOs

// DTO (Data Transfer Object): objeto que a API recebe e devolve em JSON.
// Não é tabela (sem @Entity); protege a entidade Produto.
public class ProdutoDTO {

    private Long id;          // código do produto
    private String nome;      // nome do produto
    private Double preco;     // preço do produto
    private String categoria; // categoria do produto

    // Getters e Setters: usados para ler/gravar os dados e converter de/para JSON
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}