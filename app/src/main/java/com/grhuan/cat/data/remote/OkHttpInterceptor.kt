package com.grhuan.cat.data.remote

import com.grhuan.cat.utils.PreferencesUtils
import okhttp3.Interceptor
import okhttp3.Response


class ApiKeyInterceptor(private val preferencesUtils: PreferencesUtils) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = preferencesUtils.getKeyApi() ?: ""
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", apiKey)
            .build()
        return chain.proceed(request)
    }
}