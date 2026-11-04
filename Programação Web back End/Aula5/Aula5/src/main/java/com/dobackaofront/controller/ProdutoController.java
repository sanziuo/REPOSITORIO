package com.dobackaofront.controller; // pacote dos controladores

import com.dobackaofront.dto.ProdutoDTO;                         // DTO
import com.dobackaofront.model.Produto;                          // entidade
import com.dobackaofront.service.ProdutoService;                 // serviço
import org.springframework.beans.factory.annotation.Autowired;   // injeção de dependência
import org.springframework.stereotype.Controller;                // controller de páginas HTML
import org.springframework.ui.Model;                             // envia dados para o HTML
import org.springframework.web.bind.annotation.*;                // anotações de rotas

// @Controller: devolve PÁGINAS HTML (Thymeleaf), não JSON
@Controller
// Rotas das telas começam com /produtos
@RequestMapping("/produtos")
public class ProdutoController {

    // O Spring entrega o serviço pronto
    @Autowired
    private ProdutoService produtoService;

    // GET /produtos → tela de listagem
    @GetMapping
    public String listarProdutos(Model model) {
        // Envia a lista para o HTML com o nome "produtos" (usado em ${produtos})
        model.addAttribute("produtos", produtoService.listarTodos());
        // Nome do template: templates/produto/lista.html (sem .html)
        return "produto/lista";
    }

    // GET /produtos/novo → formulário vazio de cadastro
    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        // Envia um DTO em branco com o nome "produto" (usado em th:object)
        model.addAttribute("produto", new ProdutoDTO());
        return "produto/formCadastro";
    }

    // GET /produtos/editar/{id} → formulário preenchido para edição
    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        // Busca o produto, converte em DTO e envia ao formulário
        model.addAttribute("produto", converterParaDTO(produtoService.buscarPorId(id)));
        return "produto/formCadastro";
    }

    // POST /produtos → recebe o formulário (cadastro ou edição)
    // @ModelAttribute: converte os campos do formulário em ProdutoDTO
    @PostMapping
    public String salvarProduto(@ModelAttribute ProdutoDTO produtoDTO) {
        // Sem id → cadastra; com id (campo oculto) → atualiza
        produtoService.cadastrar(converterParaEntidade(produtoDTO));
        // Manda o navegador voltar para a listagem atualizada
        return "redirect:/produtos";
    }

    // GET /produtos/excluir/{id} → exclui e volta para a lista
    // (GET porque um link <a> só dispara GET)
    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        produtoService.remover(id);
        return "redirect:/produtos";
    }

    // Converte entidade → DTO
    private ProdutoDTO converterParaDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setCategoria(produto.getCategoria());
        dto.setDescricao(produto.getDescricao());
        return dto;
    }

    // Converte DTO → entidade
    private Produto converterParaEntidade(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setDescricao(dto.getDescricao());
        return produto;
    }
}