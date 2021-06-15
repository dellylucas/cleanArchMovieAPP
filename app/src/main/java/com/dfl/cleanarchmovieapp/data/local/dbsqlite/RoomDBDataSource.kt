package com.dfl.cleanarchmovieapp.data.local.dbsqlite

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.data.mapper.MovieMap
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDBDataSource(db: DataBase) : IDataSource {

    private val movieDao = db.movieDao()

    override suspend fun getMovies(): DataResult<List<Movie>> = withContext(Dispatchers.IO) {
        DataResult.Success(MovieMap.getMoviesFromEntity(movieDao.getAllMovies()))
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        withContext(Dispatchers.IO) { movieDao.insertMovies(MovieMap.getMoviesEntity(movies)) }
    }
}