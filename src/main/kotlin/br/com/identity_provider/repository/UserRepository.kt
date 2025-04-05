package br.com.identity_provider.repository

import org.springframework.data.jpa.repository.JpaRepository
import br.com.identity_provider.model.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}
