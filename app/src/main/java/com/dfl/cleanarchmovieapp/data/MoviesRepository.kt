package com.dfl.cleanarchmovieapp.data

class MoviesRepository(private val dataSource: IDataSource) {

    suspend fun getMovies() = dataSource.getMovies()
}