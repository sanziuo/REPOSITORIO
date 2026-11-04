package com.dobackaofront;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
 * CLASSE PRINCIPAL – ponto de partida da aplicação Spring Boot.
 *
 * @SpringBootApplication junta três anotações em uma:
 *  - @Configuration         : esta classe pode declarar Beans (métodos @Bean).
 *  - @EnableAutoConfiguration: o Spring Boot configura sozinho o que encontrar
 *                              no pom.xml (banco, web etc.).
 *  - @ComponentScan          : o Spring VARRE este pacote (com.dobackaofront)
 *                              e os subpacotes, procurando classes com
 *                              @Service, @Repository, @Controller e @Component.
 *
 * É por causa do @ComponentScan que ProdutoService e ProdutoRepository
 * PRECISAM estar no mesmo pacote (ou em subpacotes) desta classe.
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		// Liga o Spring. Nesse momento ele:
		// 1. varre o pacote e encontra @Repository e @Service;
		// 2. cria um ProdutoRepository;
		// 3. cria um ProdutoService e injeta o repository no @Autowired;
		// 4. guarda tudo no container (ApplicationContext).
		SpringApplication.run(Application.class, args);
	}

	/*
	 * CommandLineRunner = código que roda automaticamente logo DEPOIS
	 * que o Spring termina de subir. Usamos só para testar.
	 *
	 * Repare no parâmetro "ProdutoService service": também não fazemos "new".
	 * Como o metodo tem @Bean, o Spring percebe que ele precisa de um
	 * ProdutoService e o entrega pronto (com o repository já injetado dentro).
	 */

	@Bean
	CommandLineRunner teste(ProdutoService service) {
		return args -> service.cadastrar("Pizza");
		// Saída esperada no console: Salvando: Pizza
	}
}