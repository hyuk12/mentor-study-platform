package com.study.mentorstudyplatform.global.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.mentorstudyplatform.global.dto.ErrorResponseDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper,
) : AuthenticationEntryPoint {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        logger.error("Unauthorized error: ${authException?.message}", authException)
        val errorResponseDto = ErrorResponseDto(
            httpStatus = HttpStatus.UNAUTHORIZED,
            message = authException?.message ?: "인증이 필요합니다.",
            errorDateTime = LocalDateTime.now(),
        )

        val responseBody = objectMapper.writeValueAsString(errorResponseDto)
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        response?.contentType = "application/json"
        response?.characterEncoding = "UTF-8"
        response?.writer?.write(responseBody)
    }
}
