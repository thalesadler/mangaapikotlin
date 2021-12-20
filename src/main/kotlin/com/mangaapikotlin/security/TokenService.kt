package com.mangaapikotlin.security

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TokenService {

    @Value("\${manga.jwt.secret}")
    lateinit var secret: String

    fun isTokenValido(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(this.secret.toByteArray()).parseClaimsJws(token).body
            true
        } catch (e: Exception) {
            false
        }
    }
}