package com.herdroid.ojekbro.data.response

import com.herdroid.ojekbro.data.entity.UserEntity

data class UserResponse(
    val current_page: Int,
    val data: UserEntity
)