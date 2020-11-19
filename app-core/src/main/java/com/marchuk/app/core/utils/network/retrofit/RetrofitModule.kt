package com.marchuk.app.core.utils.network.retrofit

import com.marchuk.app.core.BuildConfig
import com.marchuk.app.core.utils.network.retrofit.adapters.NetworkResponseAdapterFactory
import com.marchuk.app.core.utils.network.retrofit.interceptor.ApiKeyQueryInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

inline fun <reified T> provideApi(retrofit: Retrofit): T = retrofit.create(T::class.java)

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val moshiConverterFactory = MoshiConverterFactory.create(moshi)
    return Retrofit
        .Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(moshiConverterFactory)
        .build()
}

private fun provideOkHttpClient(apiKeyQueryInterceptor: ApiKeyQueryInterceptor): OkHttpClient =
    OkHttpClient()
        .newBuilder()
        .addInterceptor(apiKeyQueryInterceptor)
        .build()

val retrofitModule = module {
    single { provideRetrofit(provideOkHttpClient(ApiKeyQueryInterceptor())) }
}