# User

## Descrição
A classe `User` representa a entidade de usuário no sistema. Esta classe é responsável por armazenar as informações básicas de um usuário no banco de dados.

## Detalhes Técnicos
- Anotação: `@Entity`
- Tabela: `users`
- Localização: `com.example.identityprovider.model.User`
- Tipo: Data Class

## Atributos
- `id`: Long
  - Identificador único do usuário
  - Gerado automaticamente
  - Chave primária da tabela

- `name`: String
  - Nome do usuário
  - Campo obrigatório (nullable = false)

- `email`: String
  - Email do usuário
  - Campo obrigatório (nullable = false)
  - Valor único (unique = true)

- `password`: String
  - Senha do usuário
  - Campo obrigatório (nullable = false)

## Persistência
A classe utiliza JPA (Java Persistence API) para persistência no banco de dados, com as seguintes características:
- Mapeamento para a tabela "users"
- Geração automática de ID
- Restrições de unicidade e obrigatoriedade nos campos

## Uso
Esta classe é utilizada para:
- Armazenar informações de usuários no banco de dados
- Representar usuários em operações de autenticação
- Gerenciar dados de usuários no sistema 