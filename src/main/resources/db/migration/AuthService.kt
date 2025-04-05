package com.example.identityprovider.service

import com.example.identityprovider.dto.AuthRequest
import com.example.identityprovider.dto.RegisterRequest
import com.example.identityprovider.model.Role
import com.example.identityprovider.model.User
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
    
    fun register(request: RegisterRequest) {
        if (userRepository.existsByUsername(request.username)) {
            throw IllegalArgumentException("Username já existe")
        }
        val encodedPassword = passwordEncoder.encode(request.password)
        val user = User(
            username = request.username,
            passwordHash = encodedPassword,
            role = Role.USER,   // Define o papel padrão; ajuste conforme necessário
            enabled = true
        )
        userRepository.save(user)
    }
}
