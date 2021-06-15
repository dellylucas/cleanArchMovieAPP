package com.dfl.cleanarchmovieapp.domain.usecase

import com.dfl.cleanarchmovieapp.data.MoviesRepository
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.DataResult

class GetMovies(private val moviesRepository: MoviesRepository) {
    suspend fun getAllMovies(): DataResult<List<Movie>> = moviesRepository.getMovies()
    suspend fun getMovieById(id:Int): Movie = moviesRepository.getMovieById(id)
}