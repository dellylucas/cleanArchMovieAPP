package com.dfl.usecasesmodule

import androidx.paging.PagingData
import com.dfl.datamodule.MoviesRepository
import com.dfl.model.Movie
import com.dfl.sharedmodule.DataResult
import kotlinx.coroutines.flow.Flow

class GetMovies(private val moviesRepository: MoviesRepository) {
    fun getAllMovies(): Flow<PagingData<Movie>> = moviesRepository.getMovies()
    suspend fun getMovieById(id:Int): Movie = moviesRepository.getMovieById(id)
}