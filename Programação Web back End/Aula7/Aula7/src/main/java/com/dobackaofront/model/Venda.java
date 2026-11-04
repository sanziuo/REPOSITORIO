package com.dobackaofront.model; // pacote das entidades

import jakarta.persistence.*;        // anotações JPA (@Entity, @Id, @OneToMany...)
import java.time.LocalDateTime;      // data e hora
import java.util.ArrayList;          // implementação de lista
import java.util.List;               // tipo lista

// Entidade: vira a tabela VENDA (o pedido do cliente)
@Entity
public class Venda {

    @Id                                                  // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // id gerado pelo banco
    private Long id;                                     // coluna ID

    private LocalDateTime dataHora;  // coluna DATA_HORA: momento do registro

    // Grava o enum como TEXTO ("PENDENTE"), e não como número (0, 1, 2...)
    @Enumerated(EnumType.STRING)
    private Status status;           // coluna STATUS

    private Double valorTotal;       // coluna VALOR_TOTAL: soma dos itens

    // Uma venda tem VÁRIOS itens.
    // mappedBy = "venda": quem guarda a chave estrangeira é o atributo "venda" em VendaProduto
    // cascade = ALL: ao salvar/remover a venda, salva/remove os itens junto
    // orphanRemoval = true: item tirado da lista é apagado do banco
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendaProduto> itens = new ArrayList<>(); // começa vazia (evita NullPointerException)

    // Construtor vazio: obrigatório para o Hibernate
    public Venda() {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public List<VendaProduto> getItens() { return itens; }
    public void setItens(List<VendaProduto> itens) { this.itens = itens; }
}