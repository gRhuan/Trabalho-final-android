package com.grhuan.cat.data.remote

import com.grhuan.cat.BuildConfig
import okhttp3.OkHttpClient

object OkHttpInterceptor {
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", BuildConfig.CAT_API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()
}