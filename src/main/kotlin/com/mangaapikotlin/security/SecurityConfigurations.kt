package com.mangaapikotlin.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@Configuration
class SecurityConfigurations: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var tokenService: TokenService

    override fun configure(auth: AuthenticationManagerBuilder?) {
        super.configure(auth)
    }

    override fun configure(http: HttpSecurity?) {
        if (http != null) {
            http.authorizeRequests()
                //.antMatchers(HttpMethod.GET, "/api//manga").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(AutenticacaoViaTokenFilter(tokenService), UsernamePasswordAuthenticationFilter::class.java)
        }
    }

    override fun configure(web: WebSecurity?) {
        super.configure(web)
    }
}