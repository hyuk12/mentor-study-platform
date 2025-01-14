package com.study.mentorstudyplatform.global.user

import com.study.mentorstudyplatform.domain.user.repository.UserRepository
import com.study.mentorstudyplatform.domain.user.service.dto.of
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun loadUserByUsername(username: String): UserDetails {
        logger.debug("Attempting to load user: $username")
        val member = userRepository.findUserByEmail(username)
            ?: throw IllegalArgumentException("User not found with email: $username")
        logger.debug("User found: $username")
        val customUserInfoDto = member.of()
        return CustomUserDetails(customUserInfoDto)
    }
}
