package com.grhuan.cat.presentation.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grhuan.cat.data.repository.CatRepository
import com.grhuan.cat.presentation.RandomCatViewModel

class RandomCatViewModelFactory(private val repository: CatRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomCatViewModel::class.java)){
            return RandomCatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}