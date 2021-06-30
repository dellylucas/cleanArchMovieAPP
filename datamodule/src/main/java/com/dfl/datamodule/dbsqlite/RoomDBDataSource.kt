package com.dfl.datamodule.dbsqlite

import com.dfl.datamodule.mapper.MovieMap
import com.dfl.model.Movie
import com.dfl.datamodule.IDataSource
import com.dfl.sharedmodule.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDBDataSource(db: DataBase) : IDataSource {

    private val movieDao = db.movieDao()

    override suspend fun getMovies(page:Int): DataResult<List<Movie>> = withContext(Dispatchers.IO) {
        DataResult.Success(MovieMap.getMoviesFromEntity(movieDao.getAllMovies()))
    }

    override suspend fun getMovieById(id: Int): DataResult<Movie> = withContext(Dispatchers.IO) {
        DataResult.Success(
            MovieMap.getMovieFromEntity(movieDao.findById(id))
        )
    }

    override suspend fun saveMovies(movies: List<Movie>,page: Int) {
        withContext(Dispatchers.IO) { movieDao.insertMovies(MovieMap.getMoviesEntity(movies,page)) }
    }
}