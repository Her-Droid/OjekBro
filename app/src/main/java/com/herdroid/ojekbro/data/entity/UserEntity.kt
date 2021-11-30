package com.herdroid.ojekbro.data.entity

data class UserEntity(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val title: String,
    val address_long: String,
    val access_token: String?,
    val refresh_token: String?,
    val created_at: String?,
    val updated_at: String?
)