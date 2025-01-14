package com.study.mentorstudyplatform.domain.user.facade

import com.study.mentorstudyplatform.domain.user.controller.dto.LoginRequest
import com.study.mentorstudyplatform.domain.user.controller.dto.LoginResponse
import com.study.mentorstudyplatform.domain.user.controller.dto.toUserResponse
import com.study.mentorstudyplatform.domain.user.service.AuthService
import org.springframework.stereotype.Component

@Component
class AuthFacade(
    private val authService: AuthService,
) {
    fun login(loginRequest: LoginRequest): LoginResponse {
        val customUserInfo = authService.authenticateUser(loginRequest.email, loginRequest.password)
        val token = authService.generateToken(customUserInfo)
        return LoginResponse(token, customUserInfo.toUserResponse())
    }
}
