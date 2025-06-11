package com.grhuan.cat.data.http

import okhttp3.OkHttpClient

object OkHttpInterceptor {
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", "key")
                .build()
            chain.proceed(request)
        }
        .build()
}
