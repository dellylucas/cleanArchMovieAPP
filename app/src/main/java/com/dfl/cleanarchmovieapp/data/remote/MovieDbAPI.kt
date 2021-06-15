package com.dfl.cleanarchmovieapp.data.remote

import com.dfl.cleanarchmovieapp.utils.Constants.BASE_URL_MOVIES_DB
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDbAPI {

    val serviceClient: IMDAService = Retrofit.Builder()
        .baseUrl(BASE_URL_MOVIES_DB)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()
        .run {
            create(IMDAService::class.java)
        }

    private fun getOkHttpClient() = OkHttpClient().newBuilder()
        .addInterceptor(getLoginInterceptor())
        .build()

    private fun getLoginInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
}