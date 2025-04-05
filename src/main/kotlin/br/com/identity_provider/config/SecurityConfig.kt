package br.com.identity_provider.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration.applyDefaultSecurity
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPublicKey
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun authorizationServerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        applyDefaultSecurity(http)
        http.getConfigurer(OAuth2AuthorizationServerConfigurer::class.java)
            .oidc(Customizer.withDefaults())	// Enable OIDC 1.0
            .authorizationEndpoint { authorizationEndpoint ->
                authorizationEndpoint.consentPage("/oauth2/consent")
            }

        http.exceptionHandling { exceptions ->
            exceptions.authenticationEntryPoint(
                LoginUrlAuthenticationEntryPoint("/login")
            )
        }
            .oauth2ResourceServer { resourceServer -> resourceServer.jwt(Customizer.withDefaults()) }

        return http.build()
    }

    @Bean
    fun jwkSet(): JWKSet {
        val rsaKey = generateRsa()
        val jwk = RSAKey.Builder(rsaKey.public as RSAPublicKey)
            .keyID(UUID.randomUUID().toString())
            .privateKey(rsaKey.private)
            .build()
        return JWKSet(jwk)
    }

    @Bean
    fun rsaKey(): RSAKey {
        val rsaKey = generateRsa()
        return RSAKey.Builder(rsaKey.public as RSAPublicKey)
            .keyID(UUID.randomUUID().toString())
            .privateKey(rsaKey.private)
            .build()
    }

    private fun generateRsa(): KeyPair {
        val keyPair: KeyPair = try {
            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            keyPairGenerator.initialize(2048)
            keyPairGenerator.generateKeyPair()
        } catch (ex: Exception) {
            throw IllegalStateException(ex)
        }
        return keyPair
    }

    @Bean
    fun authorizationServerSettings(): AuthorizationServerSettings {
        return AuthorizationServerSettings.builder().issuer("http://localhost:9000").build()
    }

    @Bean
    fun users(): UserDetailsService {
        val user = User.withUsername("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN")
            .build()
        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorizeRequests ->
            authorizeRequests
                .requestMatchers("/oauth2/consent").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/auth/register").permitAll()
                .requestMatchers("/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        }
            .csrf { csrf -> csrf.disable() }
            .formLogin { formLogin -> formLogin.disable() }
            .oauth2ResourceServer { resourceServer -> resourceServer.jwt(Customizer.withDefaults()) }

        return http.build()
    }
}