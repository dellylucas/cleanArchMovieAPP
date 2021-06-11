package com.dfl.cleanarchmovieapp.domain.usecase

import com.dfl.cleanarchmovieapp.data.MoviesRepository
import com.dfl.cleanarchmovieapp.domain.model.Movie

class GetMovies(private val moviesRepository: MoviesRepository) {
    suspend fun getAllMovies(): List<Movie> = moviesRepository.getMovies()
}