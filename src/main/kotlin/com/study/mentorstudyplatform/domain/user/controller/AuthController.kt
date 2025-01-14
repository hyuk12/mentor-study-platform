package com.study.mentorstudyplatform.domain.user.controller

import com.study.mentorstudyplatform.domain.user.controller.dto.LoginRequest
import com.study.mentorstudyplatform.domain.user.controller.dto.LoginResponse
import com.study.mentorstudyplatform.domain.user.facade.AuthFacade
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authFacade: AuthFacade,
) {
    @PostMapping("/login")
    fun login(
        @RequestBody @Valid loginRequest: LoginRequest,
    ): ResponseEntity<LoginResponse> {
        val loginResponse = authFacade.login(loginRequest)
        return ResponseEntity.ok(loginResponse)
    }
}
