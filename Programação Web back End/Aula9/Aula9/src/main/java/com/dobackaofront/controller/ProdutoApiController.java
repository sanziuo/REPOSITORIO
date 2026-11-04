package com.dobackaofront.controller; // pacote dos controladores

import com.dobackaofront.dto.ProdutoDTO;                         // DTO
import com.dobackaofront.model.Produto;                          // entidade
import com.dobackaofront.service.ProdutoService;                 // serviço
import jakarta.validation.Valid;                                 // ativa a validação do DTO
import org.springframework.beans.factory.annotation.Autowired;   // injeção de dependência
import org.springframework.web.bind.annotation.*;                // anotações de rotas

import java.util.List; // lista de resultados

// @RestController: responde em JSON
// Erros de validação são tratados pelo TratadorDeErros (sem BindingResult aqui)
@RestController
@RequestMapping("/api/produtos")
public class ProdutoApiController {

    @Autowired
    private ProdutoService produtoService;

    // GET /api/produtos → lista em JSON
    @GetMapping
    public List<ProdutoDTO> listar() {
        return produtoService.listarTodos().stream().map(this::converterParaDTO).toList();
    }

    // POST /api/produtos → cadastra (JSON validado)
    @PostMapping
    public ProdutoDTO adicionar(@Valid @RequestBody ProdutoDTO dto) {
        return converterParaDTO(produtoService.cadastrar(converterParaEntidade(dto)));
    }

    // PUT /api/produtos/{id} → atualiza (JSON validado)
    @PutMapping("/{id}")
    public ProdutoDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        return converterParaDTO(produtoService.atualizar(id, converterParaEntidade(dto)));
    }

    // DELETE /api/produtos/{id} → remove
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.remover(id);
    }

    // GET /api/produtos/buscar?nome=pizza → busca por nome
    @GetMapping("/buscar")
    public List<ProdutoDTO> buscarPorNome(@RequestParam String nome) {
        return produtoService.buscarPorNome(nome).stream().map(this::converterParaDTO).toList();
    }

    // GET /api/produtos/acima-de/30 → acima de um preço
    @GetMapping("/acima-de/{preco}")
    public List<ProdutoDTO> buscarMaisCaroQue(@PathVariable Double preco) {
        return produtoService.buscarMaisCaroQue(preco).stream().map(this::converterParaDTO).toList();
    }

    // Converte entidade → DTO
    private ProdutoDTO converterParaDTO(Produto p) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setPreco(p.getPreco());
        dto.setCategoria(p.getCategoria());
        dto.setDescricao(p.getDescricao());
        return dto;
    }

    // Converte DTO → entidade
    private Produto converterParaEntidade(ProdutoDTO dto) {
        Produto p = new Produto();
        p.setId(dto.getId());
        p.setNome(dto.getNome());
        p.setPreco(dto.getPreco());
        p.setCategoria(dto.getCategoria());
        p.setDescricao(dto.getDescricao());
        return p;
    }
}