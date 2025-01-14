package com.study.mentorstudyplatform.domain.user.repository

import com.study.mentorstudyplatform.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?

    fun findUserByEmail(email: String): User?
}
