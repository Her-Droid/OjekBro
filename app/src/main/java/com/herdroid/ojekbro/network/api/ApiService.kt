package com.herdroid.ojekbro.network.api

import com.herdroid.ojekbro.data.response.LoginResponse
import com.herdroid.ojekbro.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

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

}