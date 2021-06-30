package com.dfl.datamodule.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMDAService {
    @GET("popular")
    suspend fun getProducts(
        @Query("api_key") key: String,
        @Query("language") language: String = "es-CO",
        @Query("page") page: Int
    ): Response<ResultRemote>
}