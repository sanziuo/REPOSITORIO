package com.dobackaofront;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * CAMADA DE REGRAS DE NEGÓCIO (Service)
 *
 * O Service decide O QUE fazer (validar, calcular, aplicar regras) e
 * delega ao Repository o trabalho de gravar no banco.
 * Para isso, ele PRECISA de um ProdutoRepository: essa é a sua "dependência".
 */

// @Service também transforma a classe em um Bean.
// O Spring vai criar um objeto ProdutoService e guardá-lo no container.

@Service      // também é um Bean
public class ProdutoService {

     /*
     * INJEÇÃO DE DEPENDÊNCIA com @Autowired
     *
     * SEM Spring, faríamos:
     *     private ProdutoRepository repository = new ProdutoRepository();
     *  -> a própria classe cria o objeto = ACOPLAMENTO FORTE
     *     (difícil trocar a implementação ou testar com um objeto falso).
     *
     * COM Spring, apenas declaramos o atributo e colocamos @Autowired.
     * Ao criar o ProdutoService, o Spring procura no container um Bean
     * do tipo ProdutoRepository e o "encaixa" automaticamente aqui.
     *  -> quem cria o objeto é o framework = INVERSÃO DE CONTROLE (IoC)
     *     = BAIXO ACOPLAMENTO.
     */

    @Autowired   // "Spring, me entregue um ProdutoRepository pronto"
    private ProdutoRepository repository;   // repare: sem "new"

    // Regra de negócio: cadastrar um produto.
    // Usa o repository que o Spring injetou acima.
    public void cadastrar(String produto) {
        repository.salvar(produto);
    }
}