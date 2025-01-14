package com.study.mentorstudyplatform.domain.user.controller.dto

import com.study.mentorstudyplatform.domain.user.service.dto.CustomUserInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class UserResponse(
    val id: Long,
    val email: String,
    val role: String,
    val createdAt: String,
    val updatedAt: String,
)

fun CustomUserInfo.toUserResponse(): UserResponse {
    val ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return UserResponse(
        id = memberId,
        email = email,
        role = role.name,
        createdAt = LocalDateTime.now().format(ofPattern),
        updatedAt = LocalDateTime.now().format(ofPattern),
    )
}
