package com.dfl.cleanarchmovieapp.data

import com.dfl.datamodule.IDataSourceRemote
import com.dfl.model.Movie

class FakeLocalProvider : IDataSourceRemote {

    override suspend fun getMovies(page: Int): com.dfl.sharedmodule.DataResult<List<Movie>> =
        com.dfl.sharedmodule.DataResult.Success(
            listOf(
                Movie(1, "osos", "", ""),
                Movie(2, "pez", "", ""),
                Movie(3, "aguila", "", "")
            )
        )

    override suspend fun getMovieById(id: Int): com.dfl.sharedmodule.DataResult<Movie> =
        com.dfl.sharedmodule.DataResult.Success(
            listOf(
                Movie(1, "osos", "", ""),
                Movie(2, "perro", "", "")
            ).first { it.id == id }
        )

}