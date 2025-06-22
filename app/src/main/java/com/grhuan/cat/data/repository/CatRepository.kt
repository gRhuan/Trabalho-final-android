package com.grhuan.cat.data.repository

import com.grhuan.cat.data.model.CatResponse
import com.grhuan.cat.data.service.CatService

class CatRepository(private val service: CatService) {

    suspend fun getCat(): List<CatResponse>? {
        val response = service.getCat()
        return if (response.isSuccessful) response.body() else null
    }
}