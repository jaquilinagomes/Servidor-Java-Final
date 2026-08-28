package com.labanta.servidorlocal.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServicoNaoEncontradoException.class)

    public ResponseEntity<Map<String, String>> handleServicoNaoencontrado(ServicoNaoEncontradoException ex) {
        // Enviar  um aviso ao administradorda plataforma
        log.warn("Tentativa de acesso a um recuso inexistente: {}", ex.getMessage());

        // JSON hashmap
        Map<String, String> resposta = new HashMap<>();
        resposta.put("erro", "Recurso nao encontrado");
        resposta.put("detalhes", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }
    //Exercicio 11
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(
            IllegalArgumentException ex) {

        Map<String, String> resposta = new HashMap<>();
        resposta.put("erro", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resposta);
    }
    @ExceptionHandler(UtilizadorExistenteException.class)
    public ResponseEntity<Map<String, String>> handleUtilizadorExistente(
            UtilizadorExistenteException ex) {

        Map<String, String> resposta = new HashMap<>();

        resposta.put("erro", "Username já existe");
        resposta.put("detalhes", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resposta);
    }
}
