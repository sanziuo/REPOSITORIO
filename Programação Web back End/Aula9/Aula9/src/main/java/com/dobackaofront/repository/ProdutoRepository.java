package com.dobackaofront.repository; // pacote de acesso ao banco

import com.dobackaofront.model.Produto;                          // entidade Produto
import org.springframework.data.jpa.repository.JpaRepository;    // interface com métodos prontos
import org.springframework.stereotype.Repository;                // anotação de repositório

import java.util.List; // lista de resultados

// Repositório: componente que acessa o banco de dados
@Repository
// Interface: o Spring cria a implementação sozinho
// JpaRepository<Produto, Long> = <entidade, tipo do id>
// Já traz: save, findAll, findById, deleteById, existsById, count
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca produtos cujo nome contém o texto → WHERE nome LIKE %texto%
    List<Produto> findByNomeContaining(String nome);

    // Busca produtos com preço entre dois valores → WHERE preco BETWEEN min AND max
    List<Produto> findByPrecoBetween(Double precoMinimo, Double precoMaximo);

    // Busca produtos com preço maior que o valor → WHERE preco > valor
    List<Produto> findByPrecoGreaterThan(Double preco);
}