package com.grhuan.cat.data.remote

import com.grhuan.cat.data.service.CatService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpInterceptor.client)
        .build()
    val service: CatService = retrofit.create(CatService::class.java)
}

