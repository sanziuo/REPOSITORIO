package com.dobackaofront; // pacote base do projeto

import org.springframework.boot.SpringApplication;                     // classe que inicia o Spring
import org.springframework.boot.autoconfigure.SpringBootApplication;   // anotação principal

// Marca a classe principal: ativa configuração automática
// e procura componentes neste pacote e nos subpacotes
@SpringBootApplication
public class RestauranteApplication {

    // Método principal: ponto de partida do programa
    public static void main(String[] args) {
        // Inicia o Spring, cria os objetos (Beans), o banco e o servidor na porta 8080
        SpringApplication.run(RestauranteApplication.class, args);
    }
}