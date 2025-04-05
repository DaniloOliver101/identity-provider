# Comandos cURL para Teste da API de Login

## 1. Endpoint de Autenticação (Login e Geração de Token)
Envie uma requisição POST para autenticar o usuário e obter o token JWT.

```bash
curl -X POST http://localhost:8080/api/v1/auth/authenticate \
  -H "Content-Type: application/json" \
  -d '{"username": "seu_usuario", "password": "sua_senha"}'
```

## 2. Endpoint de Registro de Usuário
Envie uma requisição POST para cadastrar um novo usuário.

```bash
curl -X POST http://localhost:9000/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "novo_usuario", "password": "sua_senha"}'
```

> Obs.: Certifique-se de substituir os valores de "seu_usuario", "sua_senha" e "novo_usuario" conforme suas necessidades.
