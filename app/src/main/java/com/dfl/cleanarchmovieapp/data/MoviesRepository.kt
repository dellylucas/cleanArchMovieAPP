package com.dfl.cleanarchmovieapp.data

import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.DataResult

class MoviesRepository(
    private val localDataSource: IDataSource,
    private val remoteDataSource: IDataSource
) {
    /**
     * Obtiene todas las peliculas de la fuente de datos
     */
    suspend fun getMovies(): DataResult<List<Movie>> {
        var result = getLocalMovies()
        //si el resultado obtenido en DB local es vacio busca en remoto
        if (result is DataResult.Success && result.data.isEmpty()) {
            result = getRemoteMovies(1)
            saveMovies(result, 1)
        }
        return result
    }

    /**
     * Obtiene todas las peliculas de la fuente de datos
     */
    suspend fun getMovies(page: Int): DataResult<List<Movie>> {
        val result = getRemoteMovies(page)
        saveMovies(result, page)
        return result
    }

    /**
     * Obtiene una pelicula de la fuente de datos local por ID
     */
    suspend fun getMovieById(id: Int): Movie =
        (localDataSource.getMovieById(id) as DataResult.Success).data

    /**
     * si se obtiene remoto se extraen y guardan en BD local
     */
    private suspend fun saveMovies(result: DataResult<List<Movie>>, page: Int) {
        if (result is DataResult.Success)
            localDataSource.saveMovies(result.data, page)
    }

    private suspend fun getLocalMovies(): DataResult<List<Movie>> =
        localDataSource.getMovies(1)

    private suspend fun getRemoteMovies(page: Int): DataResult<List<Movie>> =
        remoteDataSource.getMovies(page)

}