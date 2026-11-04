package com.dobackaofront.dto; // pacote dos DTOs

import java.time.LocalDateTime; // data e hora
import java.util.List;          // lista de itens

// DTO da venda: o que a API recebe e devolve (não expõe a entidade Venda)
public class VendaDTO {

    private Long id;                     // código da venda (saída)
    private LocalDateTime dataHora;      // data/hora do registro (saída)
    private String status;               // status como texto: "PENDENTE"... (saída)
    private Double valorTotal;           // total calculado no backend (saída)
    private List<VendaProdutoDTO> itens; // itens da venda (entrada e saída)

    // Construtor vazio: necessário para converter o JSON em objeto
    public VendaDTO() {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public List<VendaProdutoDTO> getItens() { return itens; }
    public void setItens(List<VendaProdutoDTO> itens) { this.itens = itens; }
}