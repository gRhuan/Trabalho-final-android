package com.grhuan.cat.data.repository

import com.grhuan.cat.data.local.dao.Catdao
import com.grhuan.cat.data.model.CatEntity

class LocalCatPepository(private val catdao: Catdao) {
    suspend fun saveCat(cat: CatEntity){
        catdao.insert(cat)
    }

    suspend fun getAllCats(): List<CatEntity> {
        return catdao.getAll()
    }
}