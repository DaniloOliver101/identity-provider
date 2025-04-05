package com.example.identityprovider.controller

import com.example.identityprovider.dto.AuthRequest
import com.example.identityprovider.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/authenticate")
    fun authenticate(@Valid @RequestBody request: AuthRequest): ResponseEntity<String> =
        try {
            val token = authService.authenticate(request)
            ResponseEntity.ok(token)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.message)
        }
}
