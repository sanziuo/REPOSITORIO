package com.dobackaofront.dto; // pacote dos DTOs

import jakarta.validation.Valid;                 // valida em cascata os objetos da lista
import jakarta.validation.constraints.NotEmpty;  // lista não nula e não vazia

import java.time.LocalDateTime; // data e hora
import java.util.List;          // lista de itens

// DTO da venda: o que a API recebe e devolve
public class VendaDTO {

    private Long id;                     // saída
    private LocalDateTime dataHora;      // saída
    private String status;               // saída
    private Double valorTotal;           // saída

    // @NotEmpty: a venda precisa ter ao menos 1 item
    // @Valid: valida TAMBÉM cada VendaProdutoDTO da lista (@NotNull, @Min...)
    //         sem ele, um item com quantidade 0 passaria despercebido
    @NotEmpty(message = "A venda precisa ter ao menos um item.")
    @Valid
    private List<VendaProdutoDTO> itens;

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