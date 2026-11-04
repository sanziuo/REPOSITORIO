package com.dobackaofront.dto; // pacote dos DTOs

// DTO de um item da venda.
// ENTRADA: o cliente envia só produtoId e quantidade (nunca o preço!).
// SAÍDA: a API devolve também nome do produto, preço unitário e subtotal.
public class VendaProdutoDTO {

    private Long produtoId;        // id do produto escolhido (entrada)
    private Integer quantidade;    // quantidade pedida (entrada)

    private String nomeProduto;    // nome do produto (saída)
    private Double precoUnitario;  // preço no momento da venda (saída)
    private Double subtotal;       // precoUnitario × quantidade (saída)

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