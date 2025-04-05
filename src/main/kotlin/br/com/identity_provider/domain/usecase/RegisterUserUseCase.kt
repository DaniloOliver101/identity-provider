package br.com.identity_provider.domain.usecase

import br.com.identity_provider.domain.model.User
import br.com.identity_provider.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterUserUseCase(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun execute(username: String, email: String, password: String): User {
        validateInput(username, email, password)
        
        if (userRepository.existsByUsername(username)) {
            throw IllegalArgumentException("Username already exists")
        }
        
        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("Email already exists")
        }

        val encodedPassword = passwordEncoder.encode(password)
        val user = User(
            username = username,
            email = email,
            password = encodedPassword
        )

        return userRepository.save(user)
    }

    private fun validateInput(username: String, email: String, password: String) {
        if (username.isBlank()) {
            throw IllegalArgumentException("Username cannot be blank")
        }
        if (email.isBlank() || !email.contains("@")) {
            throw IllegalArgumentException("Invalid email format")
        }
        if (password.length < 8) {
            throw IllegalArgumentException("Password must be at least 8 characters long")
        }
    }
} 