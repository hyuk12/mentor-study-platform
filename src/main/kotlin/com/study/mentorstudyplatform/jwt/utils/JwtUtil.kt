package com.study.mentorstudyplatform.jwt.utils

import com.study.mentorstudyplatform.domain.user.service.dto.CustomUserInfo
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class JwtUtil(
    @Value("\${spring.jwt.secret.key}") secretKey: String,
    @Value("\${spring.jwt.expiration_time}") private val accessTokenExpTime: Long,
) {
    private val key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey))

    fun createAccessToken(member: CustomUserInfo): String {
        return createToken(member, accessTokenExpTime)
    }

    private fun createToken(member: CustomUserInfo, expireTime: Long): String {
        val now = ZonedDateTime.now()
        val tokenValidity = now.plusSeconds(expireTime)

        return Jwts.builder()
            .claim("memberId", member.memberId)
            .claim("email", member.email)
            .claim("role", member.role)
            .issuedAt(Date.from(now.toInstant()))
            .expiration(Date.from(tokenValidity.toInstant()))
            .signWith(key)
            .compact()
    }

    fun getUserId(token: String): String {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
            .get("email", String::class.java)
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
            true
        } catch (e: Exception) {
            when (e) {
                is io.jsonwebtoken.security.SecurityException,
                is io.jsonwebtoken.MalformedJwtException,
                -> println("Invalid JWT Token")
                is io.jsonwebtoken.ExpiredJwtException -> println("Expired JWT Token")
                is io.jsonwebtoken.UnsupportedJwtException -> println("Unsupported JWT Token")
                is IllegalArgumentException -> println("JWT claims string is empty.")
                else -> println("Unexpected error")
            }
            false
        }
    }

    fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(accessToken).payload
        } catch (e: io.jsonwebtoken.ExpiredJwtException) {
            e.claims
        }
    }
}
