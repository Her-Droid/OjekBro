package com.herdroid.ojekbro.network.api

import com.herdroid.ojekbro.data.response.LoginResponse
import com.herdroid.ojekbro.data.response.RegisterResponse
import com.herdroid.ojekbro.data.response.UserResponse
import retrofit2.http.*

interface ApiService {
    companion object{
        protected const val BASE_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJvamVrYnJvIiwiYXVkIjoib2pla2JybyIsImlhdCI6MTM1Njk5OTUyNCwibmJmIjoxMzU3MDAwMDAwLCJzdWIiOjF9.gLJWVKxBwAMXuG9rtFF-AQ-LYEuaPE-UwX6hLR4xlYA"
    }
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ): LoginResponse


    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email:String,
        @Field("password") password:String
    ): RegisterResponse

    @FormUrlEncoded
    @GET("address?Page=1")
    @Headers("Authorization: $BASE_TOKEN")
    suspend fun userPage(
        @Field("title") title: String,
        @Field("address_long") address_long: String
    ): UserResponse




}