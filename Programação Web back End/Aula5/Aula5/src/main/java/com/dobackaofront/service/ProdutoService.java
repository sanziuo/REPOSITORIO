package com.dobackaofront.service; // pacote das regras de negócio

import com.dobackaofront.model.Produto;                         // entidade
import com.dobackaofront.repository.ProdutoRepository;          // repositório
import org.springframework.beans.factory.annotation.Autowired;  // injeção de dependência
import org.springframework.stereotype.Service;                  // anotação de serviço

import java.util.List; // lista de resultados

// Serviço: regras de negócio, entre o Controller e o Repository
@Service
public class ProdutoService {

    // O Spring entrega o repositório pronto
    @Autowired
    private ProdutoRepository produtoRepository;

    // Retorna todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // NOVO (Cap. 5): busca um produto pelo id (usado na tela de edição)
    // findById devolve Optional; orElseThrow lança erro se não existir
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    // Salva: sem id → INSERT; com id → UPDATE
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Atualiza um produto existente
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        produtoAtualizado.setId(id);
        return produtoRepository.save(produtoAtualizado);
    }

    // Remove pelo id
    public void remover(Long id) {
        produtoRepository.deleteById(id);
    }

    // Busca por parte do nome
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    // Busca por faixa de preço
    public List<Produto> buscarPorFaixaDePreco(Double min, Double max) {
        return produtoRepository.findByPrecoBetween(min, max);
    }

    // Busca acima de um preço
    public List<Produto> buscarMaisCaroQue(Double preco) {
        return produtoRepository.findByPrecoGreaterThan(preco);
    }
}