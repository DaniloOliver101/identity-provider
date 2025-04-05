# Casos de Uso da API de Login e Gerenciamento de Token

## 1. Login e Geração de Token

### Descrição
O cliente envia uma requisição POST para o endpoint de autenticação, informando usuário e senha. Se as credenciais estiverem corretas, a API gera um token JWT com validade definida (por exemplo, 24 horas) e o retorna na resposta.

### Atores
- Cliente (aplicação que consome a API)

### Pré-condições
- O usuário possui cadastro válido.
- A aplicação está configurada para enviar as credenciais via HTTPS.

### Fluxo Principal
1. O cliente envia uma requisição POST em `/api/v1/auth/authenticate` com o payload:
   ```json
   {
     "username": "exemploUser",
     "password": "senhaSegura"
   }
   ```
2. A API valida as credenciais e, se corretas, gera um token JWT.
3. A API retorna o token na resposta:
   ```json
   {
     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
   }
   ```

### Pós-condições
- O cliente receberá o token e deverá armazená-lo de forma segura.
- O token poderá ser anexado em requisições futuras em outros serviços através do header `Authorization: Bearer <token>`.

### Melhores Práticas
- Utilize HTTPS para todas as comunicações.
- Armazene o token de forma segura (evite armazenamento em local storage em aplicações web, quando possível).
- Implemente expiração e logout para revogar tokens comprometidos.

---

## 2. Validação do Token

### Descrição
Outras aplicações podem validar um token chamando um endpoint específico, que verifica se o token é válido e não expirou.

### Atores
- Serviço de outra aplicação dentro da mesma arquitetura de micro serviços.

### Pré-condições
- O cliente possui um token válido obtido anteriormente.

### Fluxo Principal
1. A aplicação chama o endpoint destinado à validação, por exemplo, `/api/v1/auth/validate`, passando o token no header `Authorization`.
2. A API processa o token e verifica:
   - Sua integridade (assinatura JWT).
   - Se o token não expirou.
3. A API retorna uma resposta indicando se o token é válido ou, em caso negativo, o motivo da falha.

### Pós-condições
- Aplicações validam a autenticidade e validade do token antes de autorizar o acesso.
  
### Melhores Práticas
- Centralize a lógica de validação do token para facilitar manutenção.
- Garanta que o endpoint de validação esteja protegido contra acessos indevidos.

---

## 3. Renovação do Token

### Descrição
O cliente pode solicitar a renovação do token próximo ao vencimento. Um endpoint específico receberá o token atual e, se válido, gerará e retornará um novo token com um novo período de validade.

### Atores
- Cliente (aplicação que consome a API)

### Pré-condições
- O token enviado para renovação deve estar próximo da expiração, mas ainda válido.

### Fluxo Principal
1. O cliente envia uma requisição POST para `/api/v1/auth/refresh` com o token atual no header `Authorization`.
2. A API valida o token e, se apropriado, gera um novo token com uma nova data de expiração.
3. A API retorna o novo token na resposta:
   ```json
   {
     "token": "novo_token_gerado..."
   }
   ```

### Pós-condições
- O cliente passa a utilizar o novo token em requisições futuras.

### Melhores Práticas
- Defina políticas claras para quando um token pode ser renovado (por exemplo, dentro dos últimos 10 minutos antes da expiração).
- Mantenha registros de renovação para auditoria e monitoramento.
- Considere implementar uma estratégia de refresh token separada para maior segurança.

---

## Considerações Gerais

- **Segurança:**  
  Todas as operações devem ocorrer sobre HTTPS. Gerencie a sequência de expiração, renovação e revogação de tokens de forma robusta.

- **Integração:**  
  Outras aplicações devem ser configuradas para extrair e enviar o token no header de requisições (`Authorization: Bearer <token>`). Utilize bibliotecas compatíveis com JWT para verificar o token na camada de cliente, se necessário.

- **Documentação e Testes:**  
  Documente os endpoints com Swagger/SpringDoc e implemente testes de integração para garantir que a geração, validação e renovação de tokens funcionem conforme o esperado.

- **Padrões e Boas Práticas:**  
  Seguir padrões REST, modularizar a lógica de autenticação e utilizar injeção de dependências para facilitar manutenção e escalabilidade da solução.
