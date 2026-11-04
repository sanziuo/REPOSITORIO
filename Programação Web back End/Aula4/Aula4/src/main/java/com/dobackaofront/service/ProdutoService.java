package com.dobackaofront.service; // pacote das regras de negócio

import com.dobackaofront.model.Produto;                              // entidade
import com.dobackaofront.repository.ProdutoRepository;               // repositório
import org.springframework.beans.factory.annotation.Autowired;       // injeção de dependência
import org.springframework.stereotype.Service;                       // anotação de serviço

import java.util.List; // lista de resultados

// Serviço: camada de regras de negócio, entre o Controller e o Repository
@Service
public class ProdutoService {

    // O Spring entrega o repositório pronto (sem "new")
    @Autowired
    private ProdutoRepository produtoRepository;

    // Retorna todos os produtos do banco
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Salva um produto novo (INSERT)
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Atualiza um produto existente (UPDATE)
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        produtoAtualizado.setId(id);                       // define qual produto será alterado
        return produtoRepository.save(produtoAtualizado);  // com id, o save atualiza
    }

    // Remove um produto pelo id (DELETE)
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

    // Busca produtos acima de um preço (exercício de fixação)
    public List<Produto> buscarMaisCaroQue(Double preco) {
        return produtoRepository.findByPrecoGreaterThan(preco);
    }
}