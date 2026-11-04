package com.dobackaofront.repository; // pacote de acesso ao banco

import com.dobackaofront.model.Venda;                            // entidade Venda
import org.springframework.data.jpa.repository.JpaRepository;    // interface com métodos prontos
import org.springframework.stereotype.Repository;                // anotação de repositório

// Repositório de vendas: herda save, findAll, findById, deleteById...
// (os itens são salvos junto por causa do cascade na entidade Venda)
@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}