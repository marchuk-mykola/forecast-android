package com.marchuk.app.core.utils.network.retrofit.interceptor

import com.marchuk.app.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Simple interceptor which adds API_KEY as query parameter to the request.
 */
class ApiKeyQueryInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url().newBuilder().addQueryParameter("key", BuildConfig.API_KEY).build()

        val changedRequest = original
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(changedRequest)
    }

}