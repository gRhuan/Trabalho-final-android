package com.grhuan.cat.data.repository

import com.grhuan.cat.data.model.CatResponse
import com.grhuan.cat.data.service.CatService

class RemoteCatRepository(private val service: CatService) {
    suspend fun getRandomCat(): Result<CatResponse> {
        return try {
            val response = service.getRandomCat().firstOrNull()

            if (response != null){
                Result.success(response)
            }else {
                Result.failure(Exception("Resposta vazia"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}