package com.study.mentorstudyplatform.domain.user.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String,
    val password: String,
    val email: String,
    val role: Role,
    val createdAt: String,
    val updatedAt: String,
)

enum class Role {
    USER,
    ADMIN,
    MENTOR,
    STUDENT,
    GUEST,
}
