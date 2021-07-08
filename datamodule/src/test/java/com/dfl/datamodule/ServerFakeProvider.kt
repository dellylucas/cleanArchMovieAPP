package com.dfl.datamodule

import com.dfl.datamodule.mapper.MovieMap
import com.dfl.datamodule.remote.IMDAService
import com.dfl.model.Movie
import com.dfl.sharedmodule.DataResult
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit


class ServerFakeProvider : IDataSource {

    val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(IMDAService::class.java)

    override suspend fun getMovies(page: Int): DataResult<List<Movie>> {

        val result = api.getProducts(
            key = "",
            page = page
        )

        return if (result.isSuccessful && result.body() != null)
            DataResult.Success(MovieMap.getItems(result.body()!!.results, page))
        else DataResult.Error(Exception(result.message() + " " + result.code()))
    }

    override suspend fun getMovieById(id: Int): DataResult<Movie> {
        TODO("Not yet implemented")
    }

}

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}