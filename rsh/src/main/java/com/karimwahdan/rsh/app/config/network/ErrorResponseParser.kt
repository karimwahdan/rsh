package com.karimwahdan.rsh.app.config.network

import com.karimwahdan.rsm.ErrorResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ErrorResponseParser {
    fun parseError(errorBody:String): ErrorResponse?{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter = moshi.adapter(ErrorResponse::class.java)
        return adapter.fromJson(errorBody)


    }
}