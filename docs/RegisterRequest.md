# RegisterRequest

## Descrição
A classe `RegisterRequest` é um Data Transfer Object (DTO) utilizado para encapsular os dados necessários para o registro de um novo usuário no sistema.

## Detalhes Técnicos
- Localização: `com.example.identityprovider.dto.RegisterRequest`
- Tipo: Data Class

## Atributos
- `name`: String
  - Nome do usuário a ser registrado
  - Campo obrigatório

- `email`: String
  - Email do usuário a ser registrado
  - Campo obrigatório

- `password`: String
  - Senha do usuário a ser registrado
  - Campo obrigatório

## Funcionalidades
- Encapsulamento dos dados necessários para registro
- Validação dos campos obrigatórios
- Transferência de dados entre camadas da aplicação

## Uso
Esta classe é utilizada para:
- Receber dados de registro de novos usuários
- Validar informações de registro
- Transferir dados de registro entre a camada de API e a camada de serviço 