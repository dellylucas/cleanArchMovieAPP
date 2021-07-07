package com.dfl.datamodule

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dfl.datamodule.mapper.MovieMap
import com.dfl.model.Movie
import com.dfl.sharedmodule.Constants
import com.dfl.sharedmodule.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val localDataSource: IDataSourceLocal,
    private val remoteDataSource: IDataSourceRemote
) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    /**
     * Obtiene todas las peliculas de la fuente de datos
     */
    fun getMovies(): Flow<PagingData<Movie>> {

        Log.d(Constants.TRACK_INFO, "Repo: get all movies")

        val pagingSourceFactory = { localDataSource.getMovies() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            initialKey = null,
            remoteMediator = MovieRemoteMediator(
                remoteDataSource,
                localDataSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map {
                MovieMap.getMovieFromEntity(it)
            }
        }
    }

    /**
     * Obtiene todas las peliculas de la fuente de datos
     */
    suspend fun getMovies(page: Int): DataResult<List<Movie>> {
        Log.d(Constants.TRACK_INFO, "Repo: get  movies remote page $page")
        val result = getRemoteMovies(page)
        saveMovies(result, page)
        return result
    }

    /**
     * Obtiene una pelicula de la fuente de datos local por ID
     */
    suspend fun getMovieById(id: Int): Movie {
        Log.d(Constants.TRACK_INFO, "Repo: get movie local id $id")
        return withContext(Dispatchers.IO) { MovieMap.getMovieFromEntity(localDataSource.getMovieById(id))}
    }

    /**
     * si se obtienen peliculas por fuente remota se guardan en BD local
     */
    private suspend fun saveMovies(result: DataResult<List<Movie>>, page: Int = 1) {
        Log.d(Constants.TRACK_INFO, "Repo: save movies remote page $page")
        if (result is DataResult.Success)
            localDataSource.saveMovies(MovieMap.getMoviesEntity(result.data, page))
    }


    private suspend fun getRemoteMovies(page: Int = 1): DataResult<List<Movie>> =
        remoteDataSource.getMovies(page)

}
