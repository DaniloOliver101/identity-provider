package br.com.identity_provider.service

import br.com.identity_provider.model.User
import br.com.identity_provider.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun registerUser(username: String, password: String): User {
        if (userRepository.findByUsername(username).isPresent) {
            throw RuntimeException("Usuário já existe!")
        }

        val encryptedPassword = passwordEncoder.encode(password)
        val newUser = User(username = username, password = encryptedPassword)

        return userRepository.save(newUser)
    }
}
