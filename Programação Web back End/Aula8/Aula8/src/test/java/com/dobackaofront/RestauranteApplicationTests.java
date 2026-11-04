package com.dobackaofront; // mesmo pacote base da aplicação

import org.junit.jupiter.api.Test;                           // anotação de teste (JUnit)
import org.springframework.boot.test.context.SpringBootTest; // sobe o Spring para o teste

// Teste de integração: inicia a aplicação inteira
@SpringBootTest
class RestauranteApplicationTests {

    // Passa se o Spring conseguir iniciar sem erros
    @Test
    void contextLoads() {
    }
}