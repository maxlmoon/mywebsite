package com.max.website.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationProvider: AuthenticationProvider
) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { cors ->
                cors.configurationSource {
                    CorsConfiguration().apply {
                        allowCredentials = true
                        allowedOrigins = listOf("http://localhost:5173")
                        allowedHeaders = listOf("*")
                        allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    }
                }
            }
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/blogposts/**").permitAll() // Allow everyone to read blog posts
                    .requestMatchers(HttpMethod.POST, "/api/blogposts/**").authenticated() // Only authenticated users can create blog posts
                    .requestMatchers(HttpMethod.PUT, "/api/blogposts/**").authenticated() // Only authenticated users can update blog posts
                    //.requestMatchers(HttpMethod.DELETE, "/api/blogposts/**").hasAuthority("ROLE_ADMIN") // Uncomment when roles are implemented
                    .anyRequest().authenticated()
            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}