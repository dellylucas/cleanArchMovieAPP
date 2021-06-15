package com.dfl.cleanarchmovieapp.data

import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.DataResult

class MoviesRepository(
    private val localDataSource: IDataSource,
    private val remoteDataSource: IDataSource
) {
    suspend fun getMovies(): DataResult<List<Movie>> {
        val resultLocal = getLocalMovies() as DataResult.Success
        return if (resultLocal.data.isNotEmpty())
            resultLocal
        else getRemoteMovies()
    }


    private suspend fun getLocalMovies(): DataResult<List<Movie>> =
        localDataSource.getMovies()

    private suspend fun getRemoteMovies(): DataResult<List<Movie>> =
        remoteDataSource.getMovies()

}