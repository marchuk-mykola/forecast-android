package com.marchuk.app.core.utils.network.retrofit

import com.marchuk.app.core.utils.TAG
import com.marchuk.app.core.utils.network.Result
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

internal class NetworkResponseCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<Result<S, E>> {

    override fun enqueue(callback: Callback<Result<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val error = response.errorBody()
                Timber.tag(TAG).i("Network response $body")
                Timber.tag(TAG).i("Network errorBody $error")

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(Result.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(Result.Error.Unknown(null))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(Result.Error.Api(errorBody))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(Result.Error.Unknown(null))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                throwable.printStackTrace()
//                Timber.tag(TAG).i("Network response ${throwable.stackTrace}")
                val networkResponse = when (throwable) {
                    is IOException -> Result.Error.Domain(throwable)
                    else -> Result.Error.Unknown(throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted
    override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)
    override fun isCanceled() = delegate.isCanceled
    override fun cancel() = delegate.cancel()
    override fun execute(): Response<Result<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = Timeout.NONE
}