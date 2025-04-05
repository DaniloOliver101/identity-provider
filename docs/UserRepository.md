# UserRepository

## Descrição
A interface `UserRepository` é responsável por gerenciar as operações de persistência relacionadas à entidade User. Esta interface estende o JpaRepository do Spring Data, fornecendo operações CRUD básicas e métodos personalizados para busca de usuários.

## Detalhes Técnicos
- Anotação: `@Repository`
- Localização: `com.example.identityprovider.repository.UserRepository`
- Tipo: Interface
- Herança: `JpaRepository<User, Long>`

## Métodos
### Métodos Herdados do JpaRepository
- Operações CRUD básicas (Create, Read, Update, Delete)
- Métodos de paginação
- Métodos de ordenação
- Métodos de busca por exemplo

### Métodos Personalizados
- `findByEmail(email: String): User?`
  - Busca um usuário pelo email
  - Retorna null se nenhum usuário for encontrado
  - Parâmetros:
    - email: String - Email do usuário a ser buscado

## Funcionalidades
- Persistência de dados de usuários
- Busca de usuários por ID
- Busca de usuários por email
- Operações de atualização e remoção de usuários
- Suporte a paginação e ordenação

## Uso
Esta interface é utilizada para:
- Realizar operações de banco de dados relacionadas a usuários
- Implementar a camada de acesso a dados
- Fornecer métodos de busca personalizados para usuários 