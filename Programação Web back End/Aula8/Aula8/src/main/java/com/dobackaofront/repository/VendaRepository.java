package com.dobackaofront.repository; // pacote de acesso ao banco

import com.dobackaofront.model.Status;                           // enum de status
import com.dobackaofront.model.Venda;                            // entidade Venda
import org.springframework.data.domain.Sort;                     // ordenação dos resultados
import org.springframework.data.jpa.repository.JpaRepository;    // métodos prontos
import org.springframework.data.jpa.repository.Query;            // consulta escrita à mão (JPQL)
import org.springframework.data.repository.query.Param;          // nome do parâmetro na consulta
import org.springframework.stereotype.Repository;                // anotação de repositório

import java.util.List; // lista de resultados

// Repositório de vendas
@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    // Query Method: WHERE status = ? (com ordenação)
    List<Venda> findByStatus(Status status, Sort sort);

    // Query Method de contagem: SELECT COUNT(*) ... WHERE status = ?
    long countByStatus(Status status);

    // JPQL: soma dos valores das vendas de um status.
    // COALESCE devolve 0 quando não há nenhuma venda (em vez de null)
    @Query("SELECT COALESCE(SUM(v.valorTotal), 0) FROM Venda v WHERE v.status = :status")
    Double somarValorPorStatus(@Param("status") Status status);
}