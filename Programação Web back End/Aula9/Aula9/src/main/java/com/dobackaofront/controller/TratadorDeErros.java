package com.dobackaofront.controller; // pacote dos controladores

import org.springframework.http.HttpStatus;                              // códigos HTTP (400, 404...)
import org.springframework.http.ResponseEntity;                          // resposta com status + corpo
import org.springframework.web.bind.MethodArgumentNotValidException;     // erro lançado quando o @Valid falha
import org.springframework.web.bind.annotation.ExceptionHandler;         // método que trata uma exceção
import org.springframework.web.bind.annotation.RestController;           // usado para filtrar os controllers
import org.springframework.web.bind.annotation.RestControllerAdvice;     // tratamento central de erros

import java.util.LinkedHashMap; // mapa que mantém a ordem dos campos
import java.util.Map;           // tipo mapa (campo → mensagem)

// Tratamento CENTRAL de erros das APIs.
// annotations = RestController.class: vale SÓ para os @RestController (APIs),
// sem afetar as páginas HTML (que usam BindingResult).
@RestControllerAdvice(annotations = RestController.class)
public class TratadorDeErros {

    // Erro de validação (@Valid falhou) → 400 Bad Request
    // Resposta: { "nome": "O nome do produto é obrigatório.", "preco": "..." }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarErrosDeValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new LinkedHashMap<>();
        // Para cada campo com erro: nome do campo → mensagem definida no DTO
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                erros.put(erro.getField(), erro.getDefaultMessage())
        );
        // Erros que não são de um campo específico (ex.: regras da classe toda)
        ex.getBindingResult().getGlobalErrors().forEach(erro ->
                erros.put(erro.getObjectName(), erro.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    // Erros de regra de negócio (orElseThrow, "Produto não encontrado"...)
    // → 404 Not Found quando a mensagem diz "não encontrad", senão 400
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> tratarErroDeNegocio(RuntimeException ex) {
        String mensagem = ex.getMessage() != null ? ex.getMessage() : "Erro ao processar a requisição.";
        HttpStatus status = mensagem.contains("não encontrad") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(Map.of("erro", mensagem));
    }
}