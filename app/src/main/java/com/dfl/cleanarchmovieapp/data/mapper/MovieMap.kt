package com.dfl.cleanarchmovieapp.data.mapper

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


}