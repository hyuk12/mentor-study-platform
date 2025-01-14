package com.study.mentorstudyplatform.domain.user.controller

import com.study.mentorstudyplatform.domain.user.controller.dto.UserRequest
import com.study.mentorstudyplatform.domain.user.controller.dto.UserResponse
import com.study.mentorstudyplatform.domain.user.facade.UserFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userFacade: UserFacade,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody userRegisterRequest: UserRequest.UserRegisterRequest,
    ): ResponseEntity<UserResponse> {
        // 회원가입 로직 추가
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userFacade.register(userRegisterRequest))
    }
}
