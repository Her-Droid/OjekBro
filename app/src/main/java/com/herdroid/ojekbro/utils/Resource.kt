package com.herdroid.ojekbro.utils

import okhttp3.ResponseBody

open class Resource <out T>{
    data class Success<out T>(val value:T) : Resource <T>()
    data class Failure(
        val isNetworkError:Boolean,
        val errorCode:Int?,
        val errorBody: ResponseBody?
    ): Resource<Nothing>()


    object loading : Resource<Nothing>()
}