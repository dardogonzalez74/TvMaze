package com.dg.tvmaze.extensions

import androidx.lifecycle.ViewModel

suspend fun <T : Any> toResult(block: suspend () -> T): Result<T> =
    try { Result.success(block()) }
    catch (e: Exception) { Result.failure(e) }




