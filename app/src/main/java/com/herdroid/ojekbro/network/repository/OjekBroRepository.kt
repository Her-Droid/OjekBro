package com.herdroid.ojekbro.network.repository

import android.util.Log
import com.herdroid.ojekbro.network.api.ApiService
import com.herdroid.ojekbro.preferences.UserPreferences
import com.herdroid.ojekbro.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class OjekBroRepository(private val api: ApiService, private val userPreferences: UserPreferences) :  BaseRepository(){

    suspend fun login(email: String, password: String) = safeApiCall {
        api.login(email, password)
    }

    suspend fun register(name: String, email: String, password: String) = safeApiCall {
        api.register(name,email, password)
    }

    suspend fun saveAuthToken(token:String)
    {
        userPreferences.saveAuthToken(token)
    }
}

