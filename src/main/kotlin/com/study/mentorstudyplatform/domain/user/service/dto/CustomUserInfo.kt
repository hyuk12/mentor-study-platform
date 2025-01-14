package com.study.mentorstudyplatform.domain.user.service.dto

import com.study.mentorstudyplatform.domain.user.model.Role
import com.study.mentorstudyplatform.domain.user.model.User

data class CustomUserInfo(
    val memberId: Long,
    val email: String,
    val password: String,
    val role: Role,
)

fun User.of(): CustomUserInfo {
    return CustomUserInfo(
        memberId = id,
        email = email,
        password = password,
        role = role,
    )
}
