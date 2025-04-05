package com.example.identityprovider.service

import com.example.identityprovider.dto.AuthRequest
import com.example.identityprovider.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
) {

    fun authenticate(request: AuthRequest): String {
        // Autentica as credenciais
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )
        // Carrega os detalhes do usuário
        val userDetails = userDetailsService.loadUserByUsername(request.username)
        // Retorna o token JWT gerado
        return jwtService.generateToken(userDetails)
    }
}
