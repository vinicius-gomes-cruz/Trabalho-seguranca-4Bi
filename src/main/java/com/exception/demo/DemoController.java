package com.exception.demo;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/unsafe/exception")
    public Map<String, String> unsafeException() {
        throw new IllegalStateException("Erro ao consultar banco: user=admin, senha=supersecret123, host=db-prod");
    }

    @GetMapping("/safe/exception")
    public Map<String, String> safeException() {
        try {
            throw new IllegalStateException("Erro ao consultar banco: user=admin, senha=supersecret123, host=db-prod");
        } catch (IllegalStateException e) {
            log.error("Erro interno ao processar a requisição.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor");
        }
    }
}
