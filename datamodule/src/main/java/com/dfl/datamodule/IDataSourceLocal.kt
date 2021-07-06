package com.dfl.datamodule

import androidx.paging.PagingSource
import com.dfl.datamodule.dbsqlite.MovieEntity
import com.dfl.model.Movie
import com.dfl.sharedmodule.DataResult

interface IDataSourceLocal {
    suspend fun getRootSource(): Any? = null

    fun getMovies(): PagingSource<Int, MovieEntity>

    suspend fun getMovieById(id: Int): MovieEntity

    suspend fun saveMovies(movies: List<MovieEntity>) {}
}