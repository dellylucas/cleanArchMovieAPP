package com.dfl.datamodule.remote

import com.dfl.datamodule.IDataSourceRemote
import com.dfl.datamodule.mapper.MovieMap
import com.dfl.model.Movie
import com.dfl.sharedmodule.DataResult

class ServerDataSource(private val apiKey: String) : IDataSourceRemote {

    override suspend fun getMovies(page: Int): DataResult<List<Movie>> {

        val result = MovieDbAPI.serviceClient
            .getProducts(
                key = apiKey,
                page = page
            )

        return if (result.isSuccessful && result.body() != null)
            DataResult.Success(MovieMap.getItems(result.body()!!.results,page))
        else DataResult.Error(Exception(result.message() + " " + result.code()))
    }

}