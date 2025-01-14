package com.study.mentorstudyplatform.domain.user.controller.dto

import com.study.mentorstudyplatform.domain.user.model.Role
import com.study.mentorstudyplatform.domain.user.model.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.springframework.boot.context.properties.bind.DefaultValue
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserRequest {
    data class UserRegisterRequest(
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        val username: String,
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*()-_+=])(?=\\S+\$).{8,20}\$",
            message = "비밀번호는 8자 이상 20자 이하이며, 숫자, 대소문자, 특수문자를 모두 포함해야 합니다.",
        )
        val password: String,
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        val email: String,
        @DefaultValue("USER")
        val role: String,
    ) {
        fun toUser(passwordEncoder: PasswordEncoder): User {
            val ofPattern = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss")
            return User(
                username = username,
                password = passwordEncoder.encode(password),
                email = email,
                role = Role.valueOf(role),
                createdAt = LocalDateTime.now().format(ofPattern),
                updatedAt = LocalDateTime.now().format(ofPattern),
            )
        }
    }
}
