package com.dfl.cleanarchmovieapp.data.mapper

import com.dfl.cleanarchmovieapp.data.local.dbsqlite.MovieEntity
import com.dfl.cleanarchmovieapp.data.remote.MovieRemote
import com.dfl.cleanarchmovieapp.domain.model.Movie

object MovieMap {
    fun getItems(result: List<MovieRemote>): List<Movie> =
        result.map {
            Movie(
                id = it.id,
                name = it.title,
                description = it.overview,
                posterPath = it.posterPath,
                isAdult = it.adult
            )
        }

    //TODO implementarpara  insercion
    fun getMoviesEntity(result: List<Movie>): List<MovieEntity> = emptyList()

    /* result.map {
         Movie(
             id = it.id,
             name = it.title,
             description = it.overview,
             posterPath = it.posterPath,
             isAdult = it.adult
         )
     }*/
    fun getMoviesFromEntity(result: List<MovieEntity>): List<Movie> =
        result.map {
            Movie(
                id = it.id,
                name = it.title,
                description = it.description,
                posterPath = it.posterPath,
                isAdult = it.isAdult
            )
        }
}