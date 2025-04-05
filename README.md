# Identity Provider

Um provedor de identidade robusto construído com Spring Boot, Spring Security e Spring Authorization Server, oferecendo serviços de autenticação e autorização OAuth 2.0/OIDC.

## 🚀 Funcionalidades

- **OAuth 2.0 Authorization Server**: Endpoints completos para autorização e gerenciamento de tokens
- **OpenID Connect (OIDC)**: Suporte completo para autenticação e recuperação de informações do usuário
- **Autenticação de Usuários**: Sistema de autenticação seguro com armazenamento em banco de dados
- **Gerenciamento de Recursos**: Proteção de recursos usando tokens JWT
- **Página de Consentimento**: Interface personalizável para autorização de aplicativos cliente
- **Controle de Acesso**: Sistema de roles e permissões com suporte especial para administradores

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- PostgreSQL 12 ou superior
- Docker e Docker Compose (opcional, para execução em containers)

## 🛠️ Instalação e Configuração

### 1. Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE identity_db;
```

2. Configure as variáveis de ambiente ou application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/identity_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 2. Execução Local

1. Clone o repositório:
```bash
git clone <repository-url>
cd identity-provider
```

2. Compile o projeto:
```bash
./mvnw clean install
```

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

### 3. Execução com Docker

1. Construa a imagem Docker:
```bash
docker build -t identity-provider .
```

2. Execute o container:
```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/identity_db \
  -e SPRING_DATASOURCE_USERNAME=seu_usuario \
  -e SPRING_DATASOURCE_PASSWORD=sua_senha \
  identity-provider
```

## 🔒 Endpoints Principais

- **Autorização**: `/oauth2/authorize`
- **Token**: `/oauth2/token`
- **UserInfo**: `/userinfo`
- **JWK Set**: `/oauth2/jwks`
- **Registro de Usuário**: `/register`
- **Gerenciamento de Usuários**: `/users/**` (requer role ADMIN)

## 🔐 Configuração de Clientes OAuth2

Para registrar um novo cliente OAuth2, utilize o endpoint de registro ou configure diretamente no banco de dados:

```json
{
  "client_id": "seu_client_id",
  "client_secret": "sua_client_secret",
  "redirect_uris": ["http://localhost:3000/callback"],
  "scopes": ["openid", "profile", "email"],
  "grant_types": ["authorization_code", "refresh_token"]
}
```

## 🧪 Testes

Execute os testes automatizados:
```bash
./mvnw test
```

## 📚 Documentação Adicional

- [Spring Authorization Server Documentation](https://docs.spring.io/spring-authorization-server/reference/index.html)
- [OAuth 2.0 Specification](https://oauth.net/2/)
- [OpenID Connect Specification](https://openid.net/connect/)

## 🤝 Contribuição

1. Fork o projeto
2. Crie sua Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.