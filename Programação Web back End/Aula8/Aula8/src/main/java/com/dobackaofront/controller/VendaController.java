package com.dobackaofront.controller; // pacote dos controladores

import com.dobackaofront.model.Status;                           // enum de status
import com.dobackaofront.model.Venda;                            // entidade Venda
import com.dobackaofront.service.VendaService;                   // serviço de vendas
import org.springframework.beans.factory.annotation.Autowired;   // injeção de dependência
import org.springframework.stereotype.Controller;                // controller de páginas HTML
import org.springframework.ui.Model;                             // envia dados para o HTML
import org.springframework.web.bind.annotation.*;                // anotações de rotas

import java.util.List; // lista

// @Controller: devolve PÁGINAS HTML (Thymeleaf)
//  GET /vendas                       → lista todas
//  GET /vendas?status=CONCLUIDA      → lista filtrada
//  GET /vendas/{id}                  → detalhes de uma venda
//  GET /vendas/{id}/status/{status}  → muda o status e volta
@Controller
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    // GET /vendas → lista (com filtro opcional por status)
    // @RequestParam(required = false): funciona com ou sem ?status=...
    @GetMapping
    public String listarVendas(@RequestParam(required = false) Status status, Model model) {

        // Com filtro → só daquele status; sem filtro → todas
        List<Venda> vendas = (status != null)
                ? vendaService.listarPorStatus(status)
                : vendaService.listarTodas();

        model.addAttribute("vendas", vendas);          // lista para o th:each
        model.addAttribute("statusAtual", status);     // destaca o filtro ativo

        // Resumo no topo da página
        model.addAttribute("totalConcluidas", vendaService.contarPorStatus(Status.CONCLUIDA));
        model.addAttribute("somaConcluidas", vendaService.somarPorStatus(Status.CONCLUIDA));
        model.addAttribute("totalPendentes", vendaService.contarPorStatus(Status.PENDENTE));

        return "venda/lista"; // templates/venda/lista.html
    }

    // GET /vendas/{id} → tela de detalhes do pedido
    @GetMapping("/{id}")
    public String detalharVenda(@PathVariable Long id, Model model) {
        model.addAttribute("venda", vendaService.buscarPorId(id));
        model.addAttribute("todosStatus", Status.values()); // botões para mudar status
        return "venda/detalhes"; // templates/venda/detalhes.html
    }

    // GET /vendas/{id}/status/EM_PREPARO → muda o status pela tela e volta aos detalhes
    @GetMapping("/{id}/status/{status}")
    public String alterarStatus(@PathVariable Long id, @PathVariable Status status) {
        vendaService.alterarStatus(id, status);
        return "redirect:/vendas/" + id;
    }
}