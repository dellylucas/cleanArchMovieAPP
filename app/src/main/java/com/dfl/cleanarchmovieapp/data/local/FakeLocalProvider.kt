package com.dfl.cleanarchmovieapp.data.local

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.domain.model.Movie

class FakeLocalProvider : IDataSource {

    override suspend fun getMovies() = listOf(
        Movie(1, "osos"),
        Movie(2, "pez"),
        Movie(3, "aguila")
    )

}