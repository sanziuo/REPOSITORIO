package com.dobackaofront.controller; // pacote dos controladores

import com.dobackaofront.dto.ProdutoDTO;                         // DTO
import com.dobackaofront.model.Produto;                          // entidade
import com.dobackaofront.service.ProdutoService;                 // serviço
import jakarta.validation.Valid;                                 // ativa a validação do DTO
import org.springframework.beans.factory.annotation.Autowired;   // injeção de dependência
import org.springframework.stereotype.Controller;                // controller de páginas HTML
import org.springframework.ui.Model;                             // envia dados para o HTML
import org.springframework.validation.BindingResult;             // guarda os erros de validação
import org.springframework.web.bind.annotation.*;                // anotações de rotas

// @Controller: devolve PÁGINAS HTML (Thymeleaf)
@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // GET /produtos → tela de listagem
    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produto/lista";
    }

    // GET /produtos/novo → formulário vazio
    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("produto", new ProdutoDTO());
        return "produto/formCadastro";
    }

    // GET /produtos/editar/{id} → formulário preenchido
    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("produto", converterParaDTO(produtoService.buscarPorId(id)));
        return "produto/formCadastro";
    }

    // POST /produtos → recebe e VALIDA o formulário
    // @Valid: verifica as anotações do ProdutoDTO (@NotBlank, @Positive...)
    // @ModelAttribute("produto"): mesmo nome usado no th:object do HTML
    // BindingResult: PRECISA vir logo depois do objeto validado; guarda os erros
    @PostMapping
    public String salvarProduto(@Valid @ModelAttribute("produto") ProdutoDTO produtoDTO,
                                BindingResult result) {

        // Se houver erro, volta para o formulário com os dados digitados e as mensagens
        if (result.hasErrors()) {
            return "produto/formCadastro";
        }

        // Sem erros: salva e volta para a lista
        produtoService.cadastrar(converterParaEntidade(produtoDTO));
        return "redirect:/produtos";
    }

    // GET /produtos/excluir/{id} → exclui e volta para a lista
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