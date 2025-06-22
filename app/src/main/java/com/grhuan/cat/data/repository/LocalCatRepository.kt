package com.grhuan.cat.data.repository

import com.grhuan.cat.data.local.CatDao
import com.grhuan.cat.data.model.CatEntity

class LocalCatRepository(private val catDao: CatDao) {
    suspend fun save(cat : CatEntity){
        catDao.insert(cat)
    }

    suspend fun getAll() : List<CatEntity> {
        return catDao.getAll()
    }

    suspend fun delete(id: String){
        catDao.deleteById(id)
    }
}