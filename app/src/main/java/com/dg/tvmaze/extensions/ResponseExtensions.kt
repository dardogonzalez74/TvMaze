package com.dg.tvmaze.extensions

import com.dg.tvmaze.network.NetworkException
import retrofit2.Response

fun <T: Any> Response<T>.endpointModel(): T {
    if(isSuccessful)
        return body()?: throw NetworkException(0, "Body not received")
    throw NetworkException(code(), errorBody()?.string()?: "Error message not available")
}

