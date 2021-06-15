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
            result = getRemoteMovies()
            saveMovies(result)
        }
        return result
    }

    /**
     * si se obtiene remoto se extraen y guardan en BD local
     */
    private suspend fun saveMovies(result: DataResult<List<Movie>>) {
        if (result is DataResult.Success)
            localDataSource.saveMovies(result.data)
    }

    private suspend fun getLocalMovies(): DataResult<List<Movie>> =
        localDataSource.getMovies()

    private suspend fun getRemoteMovies(): DataResult<List<Movie>> =
        remoteDataSource.getMovies()

}