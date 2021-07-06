package com.dfl.datamodule

import com.dfl.model.Movie
import com.dfl.sharedmodule.DataResult

interface IDataSourceRemote {

    suspend fun getMovies(page: Int = 1): DataResult<List<Movie>>

}