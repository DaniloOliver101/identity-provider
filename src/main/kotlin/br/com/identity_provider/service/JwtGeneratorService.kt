package br.com.identity_provider.service

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSSigner
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class JwtGeneratorService(private val rsaKey: RSAKey) {

    fun generateToken(username: String): String {
        val now = Instant.now()

        val claimsSet = JWTClaimsSet.Builder()
            .subject(username)
            .issuer("http://localhost:8080") // Mude para a URL do seu serviço
            .issueTime(Date.from(now))
            .expirationTime(Date.from(now.plusSeconds(3600))) // 1 hora de validade
            .build()

        val signer: JWSSigner = RSASSASigner(rsaKey.toPrivateKey())

        val signedJWT = SignedJWT(
            com.nimbusds.jose.JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaKey.keyID).build(),
            claimsSet
        )

        signedJWT.sign(signer)

        return signedJWT.serialize()
    }
}
