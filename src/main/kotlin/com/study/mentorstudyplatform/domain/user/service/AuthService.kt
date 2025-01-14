package com.study.mentorstudyplatform.domain.user.service

import com.study.mentorstudyplatform.domain.user.repository.UserRepository
import com.study.mentorstudyplatform.domain.user.service.dto.CustomUserInfo
import com.study.mentorstudyplatform.domain.user.service.dto.of
import com.study.mentorstudyplatform.jwt.utils.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
) {
    fun authenticateUser(email: String, password: String): CustomUserInfo {
        val user = userRepository.findUserByEmail(email) ?: throw IllegalArgumentException("User not found with email: $email")

        if (!passwordEncoder.matches(password, user.password)) {
            throw IllegalArgumentException("Invalid password")
        }

        return user.of()
    }

    fun generateToken(customUserInfo: CustomUserInfo): String {
        return jwtUtil.createAccessToken(customUserInfo)
    }
}
