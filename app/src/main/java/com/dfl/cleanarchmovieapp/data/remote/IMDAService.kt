package com.dfl.cleanarchmovieapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMDAService {
    @GET("popular")
    suspend fun getProducts(
        @Query("api_key") key: String = "b4f4465ffe3a2ac82a211519e614e1fa",
        @Query("language") language: String = "es-CO",
        @Query("page") page: Int = 1
    ): Response<ResultRemote>
}