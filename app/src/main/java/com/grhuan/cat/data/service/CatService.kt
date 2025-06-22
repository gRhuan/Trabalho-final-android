package com.grhuan.cat.data.service

import com.grhuan.cat.data.model.CatResponse
import retrofit2.Response
import retrofit2.http.GET

interface CatService {
    @GET("images/search?size=small&has_breeds=true")
    suspend fun getCat(): Response<List<CatResponse>>
}