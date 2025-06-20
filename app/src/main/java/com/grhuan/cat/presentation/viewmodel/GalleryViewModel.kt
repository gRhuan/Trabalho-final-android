package com.grhuan.cat.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grhuan.cat.data.model.CatEntity
import com.grhuan.cat.data.repository.LocalCatPepository
import kotlinx.coroutines.launch

class GalleryViewModel(private val localRepository: LocalCatPepository): ViewModel() {
    private val _cats = MutableLiveData<List<CatEntity>>()
    val cats: LiveData<List<CatEntity>> = _cats

    fun localCats(){
        viewModelScope.launch {
            _cats.value = localRepository.getAllCats()
        }
    }

}