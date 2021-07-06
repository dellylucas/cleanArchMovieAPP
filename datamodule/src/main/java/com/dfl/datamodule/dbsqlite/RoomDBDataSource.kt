package com.dfl.datamodule.dbsqlite

import androidx.paging.PagingSource
import com.dfl.datamodule.IDataSourceLocal
import com.dfl.datamodule.IDataSourceRemote
import com.dfl.datamodule.mapper.MovieMap
import com.dfl.model.Movie
import com.dfl.sharedmodule.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDBDataSource(private val db: DataBase) : IDataSourceLocal {

    private val movieDao = db.movieDao()

    override suspend fun getRootSource(): Any = db

    override fun getMovies(): PagingSource<Int, MovieEntity> =
          movieDao.getAllMovies()

    override suspend fun getMovieById(id: Int): MovieEntity = withContext(Dispatchers.IO) {
        movieDao.findById(id)
    }

    override suspend fun saveMovies(movies: List<MovieEntity>) {
        withContext(Dispatchers.IO) {
            movieDao.insertMovies(
                    movies
            )
        }
    }
}