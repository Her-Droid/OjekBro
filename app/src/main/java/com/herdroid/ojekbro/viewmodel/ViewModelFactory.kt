package com.herdroid.ojekbro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.herdroid.ojekbro.network.repository.BaseRepository
import com.herdroid.ojekbro.network.repository.OjekBroRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(OjekBroViewModel::class.java) -> {
                OjekBroViewModel(repository  as OjekBroRepository) as T
            }
            else -> throw IllegalArgumentException("View Model Nt found")
        }
    }
}