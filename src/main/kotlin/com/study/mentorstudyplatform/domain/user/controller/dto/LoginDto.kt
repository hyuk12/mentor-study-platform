package com.study.mentorstudyplatform.domain.user.controller.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Invalid email format")
    val email: String,
    @field:NotBlank(message = "Password is required")
    val password: String,
)

data class LoginResponse(
    val token: String,
    val user: UserResponse,
)
