# Relatório de Progresso e Funcionalidades Pendentes

## Funcionalidades Implementadas
- **Registro de Usuário:**  
  - Implementado pelo endpoint `POST /api/v1/auth/register`.
  - Tratamento de duplicidade de usuário e codificação segura da senha usando BCrypt.

- **Autenticação e Geração de Token:**  
  - Implementado pelo endpoint `POST /api/v1/auth/authenticate`.
  - Geração de token JWT com validação de credenciais, utilizando beans de `AuthenticationManager`, `PasswordEncoder`, `UserDetailsService` e o `JwtService`.
  - Configuração do JWT para garantir segurança mínima exigida para HMAC-SHA256.

## Problemas Enfrentados e Soluções Adotadas
- **Tipo da Coluna 'role':**  
  - Problema: Erro na inserção devido à diferença de tipos entre a entidade e o banco.
  - Solução: Criação de migration (V3) para alterar o tipo da coluna de SMALLINT para VARCHAR.

- **Bean Missing (PasswordEncoder e AuthenticationManager):**  
  - Problema: Erros devido à ausência de definição de beans para `PasswordEncoder` e `AuthenticationManager`.
  - Solução: Criação das classes de configuração `PasswordEncoderConfig` e `SecurityConfig` para expor esses beans.

- **Erro com a Chave JWT:**  
  - Problema: Erro por chave insuficiente ou incorretamente formatada para HMAC-SHA256.
  - Solução: Ajustamos o método `getSignInKey()` do `JwtService` para garantir que a chave tenha tamanho mínimo (geração de chave dinâmica ou uso de chave Base64 válida).

- **Problema com Nomenclatura de Propriedade no Repositório:**  
  - Problema: Erro na criação de query devido à utilização de `username` em vez de `userName`.
  - Solução: Renomeação dos métodos para `findByUserName` e `existsByUserName` no `UserRepository`.

## Funcionalidades Pendentes
- **Validação do Token:**  
  - Criar endpoint para que outras aplicações validem se um token JWT é válido.
  
- **Renovação (Refresh) do Token:**  
  - Implementar endpoint que gere um novo token com base em um token próximo do vencimento.
  
- **Logout e Revogação de Tokens:**  
  - Possível implementação de mecanismo de blacklist ou alteração do status do token.
  
- **Testes Automatizados e Integração:**  
  - Desenvolvimento de testes unitários e de integração para todas as funcionalidades de autenticação e registro.
  
- **Documentação e Monitoramento:**  
  - Integração com o Springdoc OpenAPI (Swagger) para documentação e configuração do Actuator para monitoramento.

> **Observação Geral:**  
> As implementações feitas seguem boas práticas com o uso de injeção de dependência, separação de responsabilidades e configuração segura de JWT, servindo de base para futuras adições e melhorias na aplicação.

