# Ajuste do JwtService para Utilizar uma Chave Segura

Para garantir que a chave tenha o tamanho mínimo (256 bits para HS256), você pode modificar o método getSignInKey() para verificar se a chave configurada é segura o suficiente. Uma alternativa é, se a chave não for segura, gerar uma nova chave usando Keys.secretKeyFor(SignatureAlgorithm.HS256) e registrar/logá-la para posterior configuração em produção.

Exemplo de alteração no JwtService:

```kotlin
private fun getSignInKey(): Key {
    return try {
        // Tenta interpretar a chave configurada como Base64
        val keyBytes = java.util.Base64.getDecoder().decode(secret)
        if (keyBytes.size < 32)
            throw IllegalArgumentException("Chave insuficiente")
        Keys.hmacShaKeyFor(keyBytes)
    } catch (e: Exception) {
        // Gera uma nova chave segura se a configurada não for adequada
        val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
        println("Chave gerada dinamicamente (Base64): ${java.util.Base64.getEncoder().encodeToString(key.encoded)}")
        key
    }
}
```

> **Observação:** Para um ambiente de produção, é preferível configurar uma chave segura e estática em `application.yml`, por exemplo:
> 
> ```yaml
> jwt:
>   secret: <sua-chave-base64-segura>
> ```
> 
> Você pode gerar essa chave usando o método `Keys.secretKeyFor(SignatureAlgorithm.HS256)` uma vez, copiar o valor Base64 exibido e configurá-lo.

Com essa alteração, a chave usada estará de acordo com as exigências, e o erro deverá ser resolvido.
