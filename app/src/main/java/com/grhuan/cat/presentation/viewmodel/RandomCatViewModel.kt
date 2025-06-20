package com.grhuan.cat.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grhuan.cat.data.mapper.CatMappers
import com.grhuan.cat.data.model.CatResponse
import com.grhuan.cat.data.repository.LocalCatPepository
import com.grhuan.cat.data.repository.RemoteCatRepository
import kotlinx.coroutines.launch

class RandomCatViewModel (
    private val remoteRepository: RemoteCatRepository,
    private val localRepository: LocalCatPepository
) : ViewModel() {
    private val _cat = MutableLiveData<CatResponse>()
    val cat: LiveData<CatResponse> = _cat

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadRandomCat(){
        Log.d("RandomCatViewModel", "Chamou loadRandomCat()")
        viewModelScope.launch {
            val result = remoteRepository.getRandomCat()
            result.onSuccess { catResponse ->
                Log.d("RandomCatViewModel", "Recebido da API: $catResponse")
                _cat.value = catResponse
            }.onFailure { e ->
                _error.value = e.message ?: "Erro desconhecido"
            }
        }
    }

    fun saveCat() {
        val currentCat = cat.value
        if (currentCat != null) {
            viewModelScope.launch {
                localRepository.saveCat(CatMappers.toEntity(currentCat))
            }
        }
    }

}