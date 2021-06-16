package com.dfl.cleanarchmovieapp.data.local

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.DataResult

class FakeLocalProvider : IDataSource {

    override suspend fun getMovies(page:Int): DataResult<List<Movie>> =
        DataResult.Success(
            listOf(
                Movie(1, "osos", "", ""),
                Movie(2, "pez", "", ""),
                Movie(3, "aguila", "", "")
            )
        )

    override suspend fun getMovieById(id: Int): DataResult<Movie> =
        DataResult.Success(
            Movie(1, "osos", "", "")
        )

}