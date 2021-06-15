package com.dfl.cleanarchmovieapp.data.local

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.DataResult

class FakeLocalProvider : IDataSource {

    override suspend fun getMovies(): DataResult<List<Movie>> =
        DataResult.Success(
            listOf(
                Movie(1, "osos", null, null),
                Movie(2, "pez", null, null),
                Movie(3, "aguila", null, null)
            )
        )

}