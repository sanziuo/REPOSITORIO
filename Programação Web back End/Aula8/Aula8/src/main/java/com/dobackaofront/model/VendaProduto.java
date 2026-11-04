package com.dobackaofront.model; // pacote das entidades

import jakarta.persistence.*; // anotações JPA

// Entidade de associação: vira a tabela VENDA_PRODUTO.
// Cada registro é UM ITEM da venda (qual produto, quantidade e preço na hora da venda).
@Entity
public class VendaProduto {

    @Id                                                  // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // id gerado pelo banco
    private Long id;                                     // coluna ID

    // Vários itens pertencem a UMA venda (lado inverso do @OneToMany)
    @ManyToOne
    @JoinColumn(name = "venda_id")    // coluna de chave estrangeira → tabela venda
    private Venda venda;

    // Vários itens podem apontar para o MESMO produto
    @ManyToOne
    @JoinColumn(name = "produto_id")  // coluna de chave estrangeira → tabela produto
    private Produto produto;

    private Integer quantidade;       // coluna QUANTIDADE
    private Double precoUnitario;     // coluna PRECO_UNITARIO: preço congelado no momento da venda

    // Construtor vazio: obrigatório para o Hibernate
    public VendaProduto() {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Venda getVenda() { return venda; }
    public void setVenda(Venda venda) { this.venda = venda; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }
}