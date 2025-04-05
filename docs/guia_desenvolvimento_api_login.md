# Guia de Desenvolvimento da API de Login

## Abordagem Sugerida

1. **Definição do Domínio:**
   - Crie uma entidade `User` para armazenar informações de usuário.
   - Utilize um repositório (JPA) para acesso aos dados.

2. **Camada de Serviço:**
   - Crie um serviço de autenticação que valide o usuário e senha.
   - Utilize BCrypt para codificar senhas.
   - Gere um token JWT (usando a biblioteca jjwt) para sessões autenticadas.

3. **Camada de Segurança:**
   - Configure o Spring Security para interceptar requisições.
   - Implemente um filtro JWT para validar o token em cada requisição.
   - Permita acesso público apenas para o endpoint de login.

4. **Camada de Controle:**
   - Crie um endpoint POST (`/api/v1/auth/authenticate`) para receber as credenciais.
   - Retorne o token JWT gerado em resposta à autenticação correta.

5. **Dependências e Configurações:**
   - Utilize Maven para gerenciar as dependências (Spring Boot Starter Web, Spring Boot Starter Security, Spring Boot Starter Data JPA, jjwt e Kotlin).
   - Configure o `application.properties` com dados do banco e a chave secreta JWT.

## Considerações

- **Teste e Validação:**  
  Utilize testes unitários (JUnit) para validar a lógica de autenticação e integração com o banco.

- **Documentação:**  
  Integre Swagger ou SpringDoc para gerar a documentação interativa da API.

- **Segurança:**  
  Mantenha a chave JWT segura, atualize as bibliotecas conforme necessário e avalie a inclusão de outras práticas de segurança, como refresh tokens.

- **Escalabilidade:**  
  Estruture sua API de forma modular para facilitar a manutenção e evolução dentro de uma arquitetura de micro serviços.

