# Guia Inicial de Configuração e Implementação

Este guia orienta a configuração do projeto (usando Spring Initializr) e a implementação da primeira funcionalidade: Login e Geração de Token.

## 1. Criação do Projeto

- Utilize o Spring Initializr para gerar um projeto com as seguintes opções:
  - **Projeto:** Maven
  - **Linguagem:** Kotlin
  - **Spring Boot:** 3.4.2 (ou última estável)
  - **Java:** 17
  - **Dependências:** 
    - Spring Boot Starter Web
    - Spring Boot Starter Security
    - Spring Boot Starter Data JPA
    - PostgreSQL
    - Flyway Migration
    - JWT (JJWT)
    - Springdoc OpenAPI (Swagger)
  
- Nome sugerido para o app: `IdentityProvider`

## 2. Estrutura do Projeto

Organize o projeto em pacotes seguindo esta estrutura:
- `com.example.identityprovider.model` – Entidades (ex.: `User`)
- `com.example.identityprovider.repository` – Repositórios JPA
- `com.example.identityprovider.dto` – Objetos de transferência (ex.: `AuthRequest`)
- `com.example.identityprovider.service` – Lógica de negócio (ex.: `AuthService` e `JwtService`)
- `com.example.identityprovider.controller` – Endpoints REST (ex.: `AuthController`)
- `com.example.identityprovider.config` – Configurações do Spring (ex.: `SecurityConfig`)

## 3. Implementação da Primeira Funcionalidade: Login e Geração de Token

### Componentes Chave

- **Entidade User:**  
  Crie a entidade para representar os usuários. Inclua atributos como id, username, passwordHash, role e status.

- **Repositório:**  
  Crie um repositório para acessar os dados dos usuários.

- **DTO de Autenticação:**  
  Crie um objeto (por exemplo, `AuthRequest`) para receber as credenciais na requisição.

- **Serviço de Autenticação:**  
  - Implemente a validação das credenciais.
  - Gere um token JWT utilizando o JJWT.
  - Configure um tempo de expiração adequado para o token (ex.: 24 horas).

- **Controller:**  
  Exponha um endpoint POST (ex.: `/api/v1/auth/authenticate`) para receber a requisição de login e retornar o token gerado.

- **Configuração de Segurança:**  
  No `SecurityConfig`, permita que o endpoint de autenticação seja acessado sem autenticação e configure o filtro que intercepta as requisições para validar o token. 

## 4. Configuração do Banco de Dados e Migrations

- Configure o `application.properties` para conectar ao PostgreSQL.
- Utilize o Flyway para versionar as migrations (consulte o guia de migrations para detalhes).

## 5. Facilitar Implementação de Funcionalidades Futuras

- **Documentação:**  
  Utilize o Springdoc OpenAPI para gerar a documentação interativa da API. Mantenha a documentação atualizada com cada nova funcionalidade.
  
- **Testes:**  
  Configure testes unitários com JUnit e testes de integração para validar a funcionalidade de login.
  
- **Modularização:**  
  Implemente cada funcionalidade em módulos ou camadas bem definidos. Isso facilitará a adição de novas features (ex.: validação de token, renovação, logout, etc).

## 6. Próximos Passos

Após implementar a funcionalidade de login e geração de token, valide via testes e documentação. Em seguida, siga para as próximas funcionalidades:
- Validação do Token
- Renovação do Token
- Logout e Revogação de Token
- Registro de Usuário e outras funcionalidades de domínio

Este guia fornece a base para estruturar seu projeto e concentrar-se na implementação incremental. Cada nova funcionalidade pode ter um guia similar no diretório `/c:/workspace/identity-provider/docs`.

