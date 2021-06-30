package com.dfl.datamodule.mapper

import com.dfl.datamodule.dbsqlite.MovieEntity
import com.dfl.datamodule.remote.MovieRemote
import com.dfl.model.Movie

object MovieMap {
    fun getItems(result: List<MovieRemote>, page: Int): List<Movie> =
        result.map {
            Movie(
                id = it.id,
                name = it.title,
                description = it.overview,
                posterPath = it.posterPath,
                isAdult = it.adult,
                page = page
            )
        }

    fun getMoviesEntity(result: List<Movie>, page: Int): List<MovieEntity> =
        result.map {
            MovieEntity(
                id = it.id,
                title = it.name,
                description = it.description,
                posterPath = it.posterPath,
                isAdult = it.isAdult,
                page = page
            )
        }

    fun getMovieFromEntity(entity: MovieEntity): Movie =
        Movie(
            id = entity.id,
            name = entity.title,
            description = entity.description,
            posterPath = entity.posterPath,
            isAdult = entity.isAdult,
            page = entity.page
        )

    fun getMoviesFromEntity(result: List<MovieEntity>): List<Movie> =
        result.map { getMovieFromEntity(it) }
}