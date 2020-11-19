package com.marchuk.app.core.utils.network.retrofit.adapters

import com.marchuk.app.core.utils.network.Result
import com.marchuk.app.core.utils.network.retrofit.NetworkResponseCall
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResponseAdapter<S : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<Result<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<Result<S, E>> {
        return NetworkResponseCall(call, errorBodyConverter)
    }

}