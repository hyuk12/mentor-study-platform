package com.study.mentorstudyplatform.global.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponseDto(
    val httpStatus: HttpStatus,
    val message: String,
    val errorDateTime: LocalDateTime,
)
