package com.study.mentorstudyplatform.domain.user.facade

import com.study.mentorstudyplatform.domain.user.controller.dto.UserRequest
import com.study.mentorstudyplatform.domain.user.controller.dto.UserResponse
import com.study.mentorstudyplatform.domain.user.controller.dto.toUserResponse
import com.study.mentorstudyplatform.domain.user.service.UserService
import com.study.mentorstudyplatform.domain.user.service.dto.of
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserFacade(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
) {
    fun register(userRegisterRequest: UserRequest.UserRegisterRequest): UserResponse {
        userService.findByUsername(userRegisterRequest.username)?.let {
            throw IllegalArgumentException("이미 존재하는 사용자입니다.")
        }
        return userService.register(userRegisterRequest.toUser(passwordEncoder)).of().toUserResponse()
    }
}
