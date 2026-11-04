package com.dobackaofront;

import org.springframework.stereotype.Repository;

/*
 * CAMADA DE ACESSO A DADOS (Repository)
 *
 * É esta classe que, num projeto real, conversa com o banco de dados
 * (salvar, buscar, excluir produtos). Aqui ela só imprime uma mensagem
 * para podermos ver a injeção funcionando.
 */

// @Repository marca a classe como um "Bean" (componente gerenciado pelo Spring).
// Quando a aplicação sobe, o Spring encontra esta anotação e cria
// SOZINHO um objeto ProdutoRepository, guardando-o no seu "container".
// Ou seja: você nunca precisará escrever "new ProdutoRepository()".


@Repository   // avisa o Spring: "crie e gerencie esse objeto"
public class ProdutoRepository {
    // Método simples que simula a gravação de um produto no banco.
    public void salvar(String produto) {
        System.out.println("Salvando: " + produto);
    }
}