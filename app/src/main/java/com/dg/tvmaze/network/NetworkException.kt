package com.dg.tvmaze.network

class NetworkException(val code: Int, error: String) : Exception(error)
