package com.dfl.cleanarchmovieapp.data.remote

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.data.mapper.MovieMap
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.DataResult

class ServerDataSource : IDataSource {

    override suspend fun getMovies(): DataResult<List<Movie>> {

        val result = MovieDbAPI.serviceClient
            .getProducts()

        return if (result.isSuccessful && result.body() != null)
            DataResult.Success(MovieMap.getItems(result.body()!!.results))
        else DataResult.Error(Exception(result.message() + " " + result.code()))
    }

}