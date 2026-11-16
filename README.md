# Vazamento de exceções e correção segura

## Objetivo

Expõe dois cenários:

- `unsafe`: simula uma falha que vaza dados sensíveis no retorno HTTP
- `safe`: simula a correção, capturando a exceção e respondendo com um erro genérico

Nenhuma persistência foi adicionada. A aplicação é apenas um endpoint HTTP de exemplo.

## Requisitos

- Java 17+
- Maven ou Maven Wrapper

## Como executar

Na raiz do projeto:

```bash
./mvnw spring-boot:run
```

A aplicação inicia em:

```text
http://localhost:8080
```

## Endpoints disponíveis

### 1) Endpoint inseguro

```http
GET /demo/unsafe/exception
```

Esse endpoint lança uma `IllegalStateException` com uma mensagem contendo dados sensíveis, por exemplo:

```text
Erro ao consultar banco: user=admin, senha=supersecret123, host=db-prod
```

### 2) Endpoint seguro

```http
GET /demo/safe/exception
```

Esse endpoint captura a exceção, registra o erro internamente e responde com uma mensagem genérica:

```json
{
  "timestamp": "2026-11-16T22:33:34.450Z",
  "status": 500,
  "error": "Internal Server Error",
  "path": "/demo/safe/exception"
}
```