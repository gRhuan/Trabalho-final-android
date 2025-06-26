package com.grhuan.cat.presentation.newcat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grhuan.cat.data.mapper.CatMappers
import com.grhuan.cat.data.model.CatResponse
import com.grhuan.cat.data.repository.CatRepository
import com.grhuan.cat.data.repository.LocalCatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewCatViewModel(
    private val catRepository: CatRepository,
    private val localCatRepository: LocalCatRepository
) : ViewModel() {

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> get() = _loadingState

    private val _cat = MutableLiveData<CatResponse>()
    val cat: LiveData<CatResponse> = _cat

    private val _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun newCat() {
        _loadingState.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val catList = catRepository.getCat()
                catList?.firstOrNull()?.let {
                    _cat.postValue(it)
                }
            } catch (e: Exception) {
                _errorState.postValue(e.message ?: "Unknown error")
                _toastMessage.postValue("Unknown error")
            } finally {
                _loadingState.postValue(false)
            }
        }
    }

    fun saveCat() {
        val currentCat = cat.value

        if (currentCat != null) {
            if (currentCat.breeds.isNullOrEmpty()) {
                _toastMessage.postValue("Adicione sua chave da API para salvar.")
                return
            }
            viewModelScope.launch(Dispatchers.IO) {
                localCatRepository.save(CatMappers.toEntity(currentCat))
                _toastMessage.postValue("Saved!")
                newCat()
            }
        } else {
            _toastMessage.postValue("Nenhum gato para salvar. Busque um novo gato primeiro!")
        }
    }
}