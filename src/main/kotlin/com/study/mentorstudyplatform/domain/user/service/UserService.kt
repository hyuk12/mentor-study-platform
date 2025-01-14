package com.study.mentorstudyplatform.domain.user.service

import com.study.mentorstudyplatform.domain.user.model.User
import com.study.mentorstudyplatform.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun register(user: User): User {
        return userRepository.save(user)
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}
