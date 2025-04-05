# Provedor de Identidade (Identity Provider)

Este aplicativo é um Provedor de Identidade (IdP) baseado em Spring Boot que utiliza Spring Security e Spring Authorization Server para fornecer serviços de autenticação e autorização.

## Funcionalidades

*   **Servidor de Autorização OAuth 2.0**: Fornece endpoints OAuth 2.0 para autorização, emissão de tokens e revogação de tokens.
*   **Provedor OIDC**: Suporta OpenID Connect (OIDC) para autenticação e recuperação de informações do usuário.
*   **Autenticação de Usuário**: Autentica usuários usando um armazenamento de usuários em memória.
*   **Servidor de Recursos**: Protege recursos usando tokens JWT emitidos pelo servidor de autorização.
*   **Página de Consentimento Personalizável**: Fornece uma página de consentimento personalizável para autorizar aplicativos cliente.
*   **Função de Administrador**: Protege o endpoint `/users/**`, exigindo a função `ADMIN`.

## Começando

1.  **Clone o repositório**:

    ```bash
    git clone <repository-url>
    ```

2.  **Construa a aplicação**:

    ```bash
    mvn clean install
    ```

3.  **Execute a aplicação**:

    ```bash
    mvn spring-boot:run
    ```

## Testes

### 1. Obtenha um Token de Acesso (Client Credentials Grant)

Primeiro, você precisa registrar um cliente no servidor de autorização. Para simplificar, vamos supor que você tenha um cliente com `client_id` como "client" e `client_secret` como "secret".