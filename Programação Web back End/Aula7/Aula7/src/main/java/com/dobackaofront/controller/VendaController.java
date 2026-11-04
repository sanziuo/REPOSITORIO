package com.dobackaofront.controller; // pacote dos controladores

import com.dobackaofront.dto.VendaDTO;                           // DTO da venda
import com.dobackaofront.model.Status;                           // enum de status
import com.dobackaofront.service.VendaService;                   // serviço de vendas
import org.springframework.beans.factory.annotation.Autowired;   // injeção de dependência
import org.springframework.web.bind.annotation.*;                // anotações de rotas

import java.util.List; // lista

// API REST de vendas (JSON)
//  POST /vendas                       → registra uma venda
//  GET  /vendas                       → lista todas
//  GET  /vendas/{id}                  → busca uma
//  PUT  /vendas/{id}/cancelar         → cancela (exercício)
//  PUT  /vendas/{id}/status/{status}  → muda o status
@RestController
@RequestMapping("/vendas")
public class VendaController {

    // O Controller só repassa para o Service (sem regra de negócio aqui)
    @Autowired
    private VendaService vendaService;

    // POST /vendas → corpo: { "itens": [ { "produtoId": 1, "quantidade": 2 } ] }
    @PostMapping
    public VendaDTO registrarVenda(@RequestBody VendaDTO vendaDTO) {
        return vendaService.registrar(vendaDTO);
    }

    // GET /vendas → lista todas as vendas com seus itens
    @GetMapping
    public List<VendaDTO> listarVendas() {
        return vendaService.listarTodas();
    }

    // GET /vendas/{id} → uma venda específica
    @GetMapping("/{id}")
    public VendaDTO buscarVenda(@PathVariable Long id) {
        return vendaService.buscarPorId(id);
    }

    // PUT /vendas/{id}/cancelar → muda o status para CANCELADA (não apaga)
    @PutMapping("/{id}/cancelar")
    public VendaDTO cancelarVenda(@PathVariable Long id) {
        return vendaService.cancelarVenda(id);
    }

    // PUT /vendas/{id}/status/EM_PREPARO → muda para qualquer status do enum
    @PutMapping("/{id}/status/{status}")
    public VendaDTO alterarStatus(@PathVariable Long id, @PathVariable Status status) {
        return vendaService.alterarStatus(id, status);
    }
}