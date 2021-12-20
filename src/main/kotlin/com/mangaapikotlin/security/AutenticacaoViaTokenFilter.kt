package com.mangaapikotlin.security

import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AutenticacaoViaTokenFilter(tokenService: TokenService) : OncePerRequestFilter() {


    private lateinit var service: TokenService

    init {
        service = tokenService
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String = recuperarToken(request)

        val valido: Boolean = service.isTokenValido(token)

        if (!valido){
            filterChain.doFilter(request, response);
        }
    }



    private fun recuperarToken(request: HttpServletRequest): String {
        val token = request.getHeader("Authorization")
        return if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            ""
        } else token.substring(7, token.length)
    }
}