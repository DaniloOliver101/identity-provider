package br.com.identity_provider.presentation.controller

import br.com.identity_provider.domain.usecase.RegisterUserUseCase
import br.com.identity_provider.presentation.dto.UserRegistrationRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val registerUserUseCase: RegisterUserUseCase
) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody request: UserRegistrationRequest): ResponseEntity<Map<String, String>> {
        val user = registerUserUseCase.execute(
            username = request.username,
            email = request.email,
            password = request.password
        )
        
        return ResponseEntity.ok(mapOf(
            "message" to "User registered successfully",
            "username" to user.username,
            "email" to user.email
        ))
    }
} 