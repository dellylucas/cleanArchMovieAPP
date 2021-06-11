package com.dfl.cleanarchmovieapp.data

import com.dfl.cleanarchmovieapp.domain.model.Movie

interface IDataSource {
    suspend fun getMovies(): List<Movie>
}