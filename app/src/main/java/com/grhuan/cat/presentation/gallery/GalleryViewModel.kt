package com.grhuan.cat.presentation.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grhuan.cat.data.model.CatEntity
import com.grhuan.cat.data.repository.LocalCatRepository
import kotlinx.coroutines.launch

class GalleryViewModel(private val localRepository: LocalCatRepository): ViewModel() {
    private val _cats = MutableLiveData<List<CatEntity>>()
    val cats: LiveData<List<CatEntity>> = _cats

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun getCats(){
        viewModelScope.launch() {
            _cats.value = localRepository.getAll()
        }
    }

    fun removeCat(catId: String){
        viewModelScope.launch {
            localRepository.delete(catId)
            _cats.value = localRepository.getAll()
            _toastMessage.value = "$catId deleted"
        }
    }
}