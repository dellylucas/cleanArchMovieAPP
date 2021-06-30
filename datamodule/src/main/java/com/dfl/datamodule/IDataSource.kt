package com.dfl.datamodule

import com.dfl.model.Movie
import com.dfl.sharedmodule.DataResult

interface IDataSource {
    suspend fun getMovies(page: Int = 1): DataResult<List<Movie>>

    suspend fun getMovieById(id: Int): DataResult<Movie>

    suspend fun saveMovies(movies: List<Movie>, page: Int) {}
}