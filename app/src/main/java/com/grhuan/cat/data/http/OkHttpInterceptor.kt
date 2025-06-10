package com.grhuan.cat.data.http

import okhttp3.OkHttpClient

object OkHttpInterceptor {
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", "live_gG5KSKLPQbuQyV1t6njIE8na0muSSdpebUorRpW8HIJQDXingbA9o4SieDacD9bo")
                .build()
            chain.proceed(request)
        }
        .build()
}