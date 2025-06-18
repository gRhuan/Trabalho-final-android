package com.grhuan.cat.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grhuan.cat.data.model.CatResponse
import com.grhuan.cat.data.repository.CatRepository
import kotlinx.coroutines.launch

class RandomCatViewModel (private val repository: CatRepository) : ViewModel() {
    private val _cat = MutableLiveData<CatResponse>()
    val cat: LiveData<CatResponse> = _cat

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadRandomCat(){
        viewModelScope.launch {
            val result = repository.getRandomCat()
            result.onSuccess { catResponse ->
                _cat.value = catResponse
            }.onFailure { e ->
                _error.value = e.message ?: "Erro desconhecido"
            }
        }
    }

    fun saveCat(){
        //sla
    }

}