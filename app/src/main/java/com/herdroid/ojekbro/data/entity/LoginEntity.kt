package com.herdroid.ojekbro.data.entity

data class LoginEntity(
    val access_token: String,
    val id: Int,
    val email: String,
    val password: String
)