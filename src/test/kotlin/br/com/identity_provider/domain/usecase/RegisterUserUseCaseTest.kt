package br.com.identity_provider.domain.usecase

import br.com.identity_provider.domain.model.User
import br.com.identity_provider.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class RegisterUserUseCaseTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    private lateinit var registerUserUseCase: RegisterUserUseCase

    @BeforeEach
    fun setup() {
        registerUserUseCase = RegisterUserUseCase(userRepository, passwordEncoder)
    }

    @Test
    fun `should register user successfully`() {
        // Given
        val username = "testuser"
        val email = "test@example.com"
        val password = "password123"
        val encodedPassword = "encodedPassword123"
        
        whenever(userRepository.existsByUsername(username)).thenReturn(false)
        whenever(userRepository.existsByEmail(email)).thenReturn(false)
        whenever(passwordEncoder.encode(password)).thenReturn(encodedPassword)
        whenever(userRepository.save(any())).thenAnswer { it.arguments[0] as User }

        // When
        val result = registerUserUseCase.execute(username, email, password)

        // Then
        assertNotNull(result)
        assertEquals(username, result.username)
        assertEquals(email, result.email)
        assertEquals(encodedPassword, result.password)
        verify(userRepository).save(any())
    }

    @Test
    fun `should throw exception when username already exists`() {
        // Given
        val username = "existinguser"
        val email = "test@example.com"
        val password = "password123"
        
        whenever(userRepository.existsByUsername(username)).thenReturn(true)

        // When/Then
        assertThrows(IllegalArgumentException::class.java) {
            registerUserUseCase.execute(username, email, password)
        }
    }

    @Test
    fun `should throw exception when email already exists`() {
        // Given
        val username = "newuser"
        val email = "existing@example.com"
        val password = "password123"
        
        whenever(userRepository.existsByUsername(username)).thenReturn(false)
        whenever(userRepository.existsByEmail(email)).thenReturn(true)

        // When/Then
        assertThrows(IllegalArgumentException::class.java) {
            registerUserUseCase.execute(username, email, password)
        }
    }

    @Test
    fun `should throw exception when password is too short`() {
        // Given
        val username = "newuser"
        val email = "test@example.com"
        val password = "short"

        // When/Then
        assertThrows(IllegalArgumentException::class.java) {
            registerUserUseCase.execute(username, email, password)
        }
    }
} 