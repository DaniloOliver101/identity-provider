package com.example.identityprovider.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

data class ErrorResponse(
    val error: String,
    val message: String?
)

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Erro de Execução", ex.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
    //error UsernameNotFoundException
    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(ex: UsernameNotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Usuário não encontrado", ex.message)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }
//error UnauthorizedException

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: UnauthorizedException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Não autorizado", ex.message)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }





    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Erro Interno", ex.message)
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
    // error authentication exception  invalid user or password 
    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(ex: AuthenticationException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Usuário ou senha inválidos", ex.message)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }   
}
