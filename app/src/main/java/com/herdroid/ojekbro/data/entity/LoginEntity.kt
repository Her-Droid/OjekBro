package com.herdroid.ojekbro.data.entity

data class LoginEntity(
    val token: String,
    val id: Int,
    val email: String,
    val password: String
)