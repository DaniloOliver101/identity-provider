# AuthRequest

## Descrição
A classe `AuthRequest` é um Data Transfer Object (DTO) utilizado para encapsular os dados necessários para a autenticação de um usuário no sistema.

## Detalhes Técnicos
- Localização: `com.example.identityprovider.dto.AuthRequest`
- Tipo: Data Class

## Atributos
- `email`: String
  - Email do usuário para autenticação
  - Campo obrigatório

- `password`: String
  - Senha do usuário para autenticação
  - Campo obrigatório

## Funcionalidades
- Encapsulamento dos dados de autenticação
- Validação dos campos obrigatórios
- Transferência de dados entre camadas da aplicação

## Uso
Esta classe é utilizada para:
- Receber dados de autenticação de usuários
- Validar credenciais de acesso
- Transferir dados de autenticação entre a camada de API e a camada de serviço 