package br.com.identity_provider

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IdentityProviderApplication

fun main(args: Array<String>) {
	runApplication<IdentityProviderApplication>(*args)
}
