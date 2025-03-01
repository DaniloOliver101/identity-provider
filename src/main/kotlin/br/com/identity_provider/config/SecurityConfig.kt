package br.com.identity_provider.config

import com.nimbusds.jose.jwk.RSAKey
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*

@Suppress("SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection"
)
@Configuration
@EnableWebSecurity
class SecurityConfig {


    private fun generateRsaKey(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)  // Tamanho da chave
        return keyPairGenerator.generateKeyPair()
    }


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->

                auth
                    .requestMatchers("/public/**", "/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer { resourceServer ->
                resourceServer.jwt { jwtConfigurer ->
                    jwtConfigurer.decoder(jwtDecoder())
                }
            }

            .formLogin { formLogin ->
                formLogin
                    .loginPage("/login")  //
                    .permitAll()  // Permite acesso à página de login sem autenticação
            }

        return http.build()
    }

    // Bean para gerar a chave RSA (pública e privada)
    @Bean
    fun rsaKey(): RSAKey {
        val keyPair = generateRsaKey()
        val publicKey = keyPair.public as RSAPublicKey
        val privateKey = keyPair.private as RSAPrivateKey

        // Construa a chave pública e privada
        return RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())  // Gerar um ID único para a chave
            .build()
    }


    @Bean
    fun jwtDecoder(): JwtDecoder {
        val rsaKey = rsaKey()  // Pega a chave pública gerada
        return NimbusJwtDecoder.withPublicKey(rsaKey.toPublicKey() as RSAPublicKey?).build()  // Usando NimbusJwtDecoder
    }


    @Bean
    fun jwtEncoder(): JwtEncoder {
        val rsaKey = rsaKey()  // Pega a chave privada gerada
        return JwtEncoder { JwtEncoderParameters.fromKey(rsaKey.toPrivateKey()) }
    }

    // Bean para o PasswordEncoder que é usado para codificar as senhas dos usuários
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
