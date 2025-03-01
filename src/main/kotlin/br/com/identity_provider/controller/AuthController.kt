package br.com.identity_provider.controller

import br.com.identity_provider.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestParam username: String, @RequestParam password: String): ResponseEntity<String> {
        val user = userService.registerUser(username, password)
        return ResponseEntity.ok("Usuário ${user.username} cadastrado com sucesso!")
    }
}
