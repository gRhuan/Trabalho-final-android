package com.grhuan.cat.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grhuan.cat.data.repository.LocalCatPepository
import com.grhuan.cat.data.repository.RemoteCatRepository
import com.grhuan.cat.presentation.viewmodel.RandomCatViewModel

class RandomCatViewModelFactory(
    private val remoteCatRepository: RemoteCatRepository,
    private val localCatRepository: LocalCatPepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomCatViewModel::class.java)) {
            return RandomCatViewModel(remoteCatRepository, localCatRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}