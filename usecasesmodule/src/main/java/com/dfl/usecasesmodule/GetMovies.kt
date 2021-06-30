package com.dfl.usecasesmodule

import com.dfl.datamodule.MoviesRepository
import com.dfl.model.Movie
import com.dfl.sharedmodule.DataResult

class GetMovies(private val moviesRepository: MoviesRepository) {
    suspend fun getAllMovies(): DataResult<List<Movie>> = moviesRepository.getMovies()
    suspend fun getMoviesByPage(page:Int): DataResult<List<Movie>> = moviesRepository.getMovies(page)
    suspend fun getMovieById(id:Int): Movie = moviesRepository.getMovieById(id)
}