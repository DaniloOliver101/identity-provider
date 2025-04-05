package br.com.identity_provider.domain.repository

import br.com.identity_provider.domain.model.User
import java.util.Optional

interface UserRepository {
    fun save(user: User): User
    fun findById(id: Long): Optional<User>
    fun findByUsername(username: String): Optional<User>
    fun findByEmail(email: String): Optional<User>
    fun findAll(): List<User>
    fun delete(user: User)
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
} 