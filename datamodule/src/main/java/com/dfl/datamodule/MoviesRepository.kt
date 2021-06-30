package com.dfl.datamodule

import android.util.Log
import com.dfl.model.Movie
import com.dfl.sharedmodule.Constants
import com.dfl.sharedmodule.DataResult

class MoviesRepository(
    private val localDataSource: IDataSource,
    private val remoteDataSource: IDataSource
) {
    /**
     * Obtiene todas las peliculas de la fuente de datos
     */
    suspend fun getMovies(): DataResult<List<Movie>> {

        Log.d(Constants.TRACK_INFO, "Repo: get all movies local")
        var result = getLocalMovies()
        //si el resultado obtenido en DB local es vacio busca en remoto
        if (result is DataResult.Success && result.data.isEmpty()) {
            Log.d(Constants.TRACK_INFO, "Repo: get all movies remote")
            result = getRemoteMovies()
            saveMovies(result)
        }
        return result
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
        return (localDataSource.getMovieById(id) as DataResult.Success).data
    }

    /**
     * si se obtienen peliculas por fuente remota se guardan en BD local
     */
    private suspend fun saveMovies(result: DataResult<List<Movie>>, page: Int = 1) {
        Log.d(Constants.TRACK_INFO, "Repo: save movies remote page $page")
        if (result is DataResult.Success)
            localDataSource.saveMovies(result.data, page)
    }

    private suspend fun getLocalMovies(): DataResult<List<Movie>> =
        localDataSource.getMovies()

    private suspend fun getRemoteMovies(page: Int = 1): DataResult<List<Movie>> =
        remoteDataSource.getMovies(page)

}