package com.study.mentorstudyplatform.jwt.utils

import com.study.mentorstudyplatform.global.user.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val customUserDetailsService: CustomUserDetailsService,
    private val jwtUtil: JwtUtil,
) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authorization = request.getHeader("Authorization")
        logger.debug("Authorization header: $authorization")
        if (authorization?.startsWith("Bearer ") == true) {
            val token = authorization.substring(7)
            if (jwtUtil.validateToken(token)) {
                val userId = jwtUtil.getUserId(token)
                val userDetails = customUserDetailsService.loadUserByUsername(userId)

                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities,
                ).apply {
                    details = WebAuthenticationDetailsSource().buildDetails(request)
                }
                SecurityContextHolder.getContext().authentication = authentication
            } else {
                logger.warn("Invalid JWT Token")
            }
        } else {
            logger.debug("No Bearer token found in the request")
        }

        filterChain.doFilter(request, response)
    }
}
