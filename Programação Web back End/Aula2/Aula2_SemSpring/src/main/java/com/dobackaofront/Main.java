package com.dobackaofront;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        ProdutoRepository repository = new ProdutoRepository(); // "sem Spring": a classe cria o objeto
        repository.salvar("Pizza");
    }
}
