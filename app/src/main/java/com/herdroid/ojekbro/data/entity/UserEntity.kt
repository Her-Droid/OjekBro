package com.herdroid.ojekbro.data.entity

data class UserEntity(
    val id: Int,
    val title: String,
    val address_long: String,
    val coordinate: String?,
    val created_at: String?,
    val updated_at: String?
)