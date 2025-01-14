package com.study.mentorstudyplatform.global.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.mentorstudyplatform.global.dto.ErrorResponseDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CustomAccessDeniedHandler(
    private val objectMapper: ObjectMapper,
) : AccessDeniedHandler {
    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {
        val errorResponseDto = ErrorResponseDto(
            httpStatus = HttpStatus.FORBIDDEN,
            message = accessDeniedException?.message ?: "권한이 없습니다.",
            errorDateTime = LocalDateTime.now(),
        )

        val responseBody = objectMapper.writeValueAsString(errorResponseDto)
        response?.status = HttpServletResponse.SC_FORBIDDEN
        response?.contentType = "application/json"
        response?.characterEncoding = "UTF-8"
        response?.writer?.write(responseBody)
    }
}
