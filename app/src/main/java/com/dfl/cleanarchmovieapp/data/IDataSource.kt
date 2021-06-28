package com.dfl.cleanarchmovieapp.data

import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.DataResult

interface IDataSource {
    suspend fun getMovies(page: Int = 1): DataResult<List<Movie>>

    suspend fun getMovieById(id: Int): DataResult<Movie>

    suspend fun saveMovies(movies: List<Movie>, page: Int) {}
}