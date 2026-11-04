package com.dobackaofront.dto; // pacote dos DTOs

import jakarta.validation.constraints.NotBlank; // não nulo, não vazio, não só espaços
import jakarta.validation.constraints.NotNull;  // não nulo
import jakarta.validation.constraints.Positive; // número maior que zero
import jakarta.validation.constraints.Size;     // tamanho mínimo/máximo do texto

// DTO: dados que trafegam entre a tela/API e o Controller.
// As anotações definem as REGRAS; o @Valid no Controller faz a verificação.
public class ProdutoDTO {

    private Long id; // código (sem validação: vazio no cadastro)

    // Obrigatório e com no máximo 100 caracteres
    @NotBlank(message = "O nome do produto é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    // Obrigatório e maior que zero
    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    private Double preco;

    // Opcional, mas limitado a 50 caracteres
    @Size(max = 50, message = "A categoria deve ter no máximo 50 caracteres.")
    private String categoria;

    // Opcional, mas limitado a 255 caracteres (tamanho da coluna no banco)
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    private String descricao;

    // Construtor vazio: o formulário precisa criar um ProdutoDTO em branco
    public ProdutoDTO() {
    }

    // Getters e Setters
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