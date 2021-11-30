package com.herdroid.ojekbro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdroid.ojekbro.data.response.LoginResponse
import com.herdroid.ojekbro.data.response.RegisterResponse
import com.herdroid.ojekbro.network.repository.OjekBroRepository
import com.herdroid.ojekbro.utils.Resource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class OjekBroViewModel(private val ojekBroRepository: OjekBroRepository) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    private val _registerResponse: MutableLiveData<Resource<RegisterResponse>> = MutableLiveData()
    val registerResponse: LiveData<Resource<RegisterResponse>>
        get() = _registerResponse
    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.loading
        _loginResponse.value = ojekBroRepository.login(email, password)
    }


    fun register(
        name: String,
        email: String,
        password: String
    ) = viewModelScope.launch {
        _registerResponse.value = Resource.loading
        _registerResponse.value = ojekBroRepository.register(name, email, password)
    }

    suspend fun saveAuthToken(token: String)  {
        ojekBroRepository.saveAuthToken(token)
    }
}
