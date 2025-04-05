# Guia de Migrations com Flyway

## Introdução

Esta aplicação utilizará PostgreSQL como banco de dados e o Flyway para gerenciar as migrations.

## Configuração

1. A dependência do Flyway já foi adicionada ao `pom.xml`.
2. Configure o arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/identitydb
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   spring.jpa.hibernate.ddl-auto=validate
   ```

## Criando Migrations

1. Crie os arquivos de migration na pasta padrão:  
   `src/main/resources/db/migration`

2. Siga a convenção de nomenclatura do Flyway, por exemplo:  
   - `V1__create_user_table.sql`
   - `V2__add_index_to_username.sql`

## Exemplo de Migration

Conteúdo do arquivo `V1__create_user_table.sql`:
