# Configurando Baseline no Flyway

Se o schema "public" já contém objetos mas não possui a tabela de histórico do Flyway, você receberá o erro:

"Found non-empty schema(s) 'public' but no schema history table. Use baseline() or set baselineOnMigrate to true to initialize the schema history table."

Para resolver esse problema, adicione a propriedade `baselineOnMigrate: true` em sua configuração do Flyway no arquivo `application.yml`. Assim, o Flyway irá criar uma baseline do schema existente e continuar com as migrations.

Exemplo de configuração atualizada:

```yaml
flyway:
  enabled: true
  baselineOnMigrate: true
```

Com essa configuração, o Flyway criará a tabela de histórico no seu schema, permitindo que as migrations subsequentes sejam aplicadas corretamente.
