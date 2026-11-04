package com.dobackaofront.dto; // pacote dos DTOs

import jakarta.validation.constraints.Min;     // valor mínimo
import jakarta.validation.constraints.NotNull; // não nulo

// DTO de um item da venda.
// ENTRADA: produtoId e quantidade (validados).
// SAÍDA: nome do produto, preço unitário e subtotal (preenchidos pelo backend).
public class VendaProdutoDTO {

    // Obrigatório: qual produto
    @NotNull(message = "É necessário informar o produto.")
    private Long produtoId;

    // Obrigatório e pelo menos 1
    @NotNull(message = "É necessário informar a quantidade.")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
    private Integer quantidade;

    private String nomeProduto;    // saída
    private Double precoUnitario;  // saída
    private Double subtotal;       // saída

    // Construtor vazio: necessário para converter o JSON em objeto
    public VendaProdutoDTO() {
    }

    // Getters e Setters
    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}