package com.dobackaofront.controller; // pacote dos controladores (rotas)

import com.dobackaofront.dto.ProdutoDTO;                         // DTO
import com.dobackaofront.model.Produto;                          // entidade
import com.dobackaofront.service.ProdutoService;                 // serviço
import org.springframework.beans.factory.annotation.Autowired;   // injeção de dependência
import org.springframework.web.bind.annotation.*;                // anotações de rotas HTTP

import java.util.List; // lista de resultados

// Controller REST: recebe requisições HTTP e responde em JSON
@RestController
// Endereço base: todas as rotas começam com /produtos
@RequestMapping("/produtos")
public class ProdutoController {

    // O Spring entrega o serviço pronto
    @Autowired
    private ProdutoService produtoService;

    // GET /produtos → lista todos os produtos
    @GetMapping
    public List<ProdutoDTO> listarProdutos() {
        return produtoService.listarTodos().stream()  // pega a lista de entidades
                .map(this::converterParaDTO)           // converte cada uma em DTO
                .toList();                             // devolve como lista
    }

    // POST /produtos → cadastra um produto
    // @RequestBody: converte o JSON enviado em ProdutoDTO
    @PostMapping
    public ProdutoDTO adicionarProduto(@RequestBody ProdutoDTO dto) {
        Produto salvo = produtoService.cadastrar(converterParaEntidade(dto)); // DTO → entidade → salva
        return converterParaDTO(salvo);                                       // entidade → DTO (resposta)
    }

    // PUT /produtos/{id} → atualiza um produto
    // @PathVariable: pega o {id} da URL
    @PutMapping("/{id}")
    public ProdutoDTO atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        Produto atualizado = produtoService.atualizar(id, converterParaEntidade(dto));
        return converterParaDTO(atualizado);
    }

    // DELETE /produtos/{id} → remove um produto
    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.remover(id);
    }

    // GET /produtos/buscar?nome=pizza → busca por parte do nome
    // @RequestParam: pega o valor depois do "?" na URL
    @GetMapping("/buscar")
    public List<ProdutoDTO> buscarPorNome(@RequestParam String nome) {
        return produtoService.buscarPorNome(nome).stream()
                .map(this::converterParaDTO).toList();
    }

    // GET /produtos/faixa?min=10&max=50 → busca por faixa de preço
    @GetMapping("/faixa")
    public List<ProdutoDTO> buscarPorFaixa(@RequestParam Double min, @RequestParam Double max) {
        return produtoService.buscarPorFaixaDePreco(min, max).stream()
                .map(this::converterParaDTO).toList();
    }

    // GET /produtos/acima-de/30 → produtos com preço maior que o valor (exercício)
    @GetMapping("/acima-de/{preco}")
    public List<ProdutoDTO> buscarMaisCaroQue(@PathVariable Double preco) {
        return produtoService.buscarMaisCaroQue(preco).stream()
                .map(this::converterParaDTO).toList();
    }

    // Converte entidade Produto → ProdutoDTO (para enviar na resposta)
    private ProdutoDTO converterParaDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setCategoria(produto.getCategoria());
        return dto;
    }

    // Converte ProdutoDTO → entidade Produto (para salvar no banco)
    private Produto converterParaEntidade(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        return produto;
    }
}