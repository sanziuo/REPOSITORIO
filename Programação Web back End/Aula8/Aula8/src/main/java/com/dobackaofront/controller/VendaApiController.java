package com.dobackaofront.controller; // pacote dos controladores

import com.dobackaofront.dto.VendaDTO;                           // DTO da venda
import com.dobackaofront.model.Status;                           // enum de status
import com.dobackaofront.service.VendaService;                   // serviço de vendas
import org.springframework.beans.factory.annotation.Autowired;   // injeção de dependência
import org.springframework.web.bind.annotation.*;                // anotações de rotas

import java.util.List; // lista

// @RestController: responde em JSON (API do Capítulo 7)
// Fica em /api/vendas para não conflitar com as telas em /vendas
@RestController
@RequestMapping("/api/vendas")
public class VendaApiController {

    @Autowired
    private VendaService vendaService;

    // POST /api/vendas → registra uma venda
    @PostMapping
    public VendaDTO registrarVenda(@RequestBody VendaDTO vendaDTO) {
        return vendaService.registrar(vendaDTO);
    }

    // GET /api/vendas → lista em JSON
    @GetMapping
    public List<VendaDTO> listarVendas() {
        return vendaService.listarTodasDTO();
    }

    // GET /api/vendas/{id} → uma venda em JSON
    @GetMapping("/{id}")
    public VendaDTO buscarVenda(@PathVariable Long id) {
        return vendaService.buscarPorIdDTO(id);
    }

    // PUT /api/vendas/{id}/cancelar → status CANCELADA
    @PutMapping("/{id}/cancelar")
    public VendaDTO cancelarVenda(@PathVariable Long id) {
        return vendaService.converterParaDTO(vendaService.cancelarVenda(id));
    }

    // PUT /api/vendas/{id}/status/CONCLUIDA → muda o status
    @PutMapping("/{id}/status/{status}")
    public VendaDTO alterarStatus(@PathVariable Long id, @PathVariable Status status) {
        return vendaService.converterParaDTO(vendaService.alterarStatus(id, status));
    }
}