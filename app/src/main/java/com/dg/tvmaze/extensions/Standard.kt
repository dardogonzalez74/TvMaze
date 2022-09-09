package com.dg.tvmaze.extensions


suspend fun <T : Any> wrap(block: suspend () -> T): Result<T> =
    try { Result.success(block()) }
    catch (e: Exception) { Result.failure(e) }




