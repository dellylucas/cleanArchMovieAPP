package com.dfl.cleanarchmovieapp.operative.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfl.model.Movie
import com.dfl.usecasesmodule.GetMovies
import com.dfl.sharedmodule.Constants.TRACK_INFO
import com.dfl.sharedmodule.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagementMoviesVM @Inject constructor(
    private val getUseCaseMovies: GetMovies
) : ViewModel() {

    private val _load = MutableLiveData<Boolean>()
    val load: LiveData<Boolean>
        get() = _load

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _currentMovie = MutableLiveData<Movie>()
    val currentMovie: LiveData<Movie>
        get() = _currentMovie

    private var page: Int = 2

    /**
     * Obtener todas las peliculas
     */
    fun getAllMovies() {
        Log.d(TRACK_INFO, "VM: get all movies")
        viewModelScope.launch {
            _load.value = true

            when (val result = getUseCaseMovies.getAllMovies()) {
                is DataResult.Error -> Log.d(TRACK_INFO, "error: " + result.exception.message)
                is DataResult.Success -> _movies.value = result.data
            }
            _load.value = false
        }
    }

    /**
     * obtiene nuevas peliculas de la fuente remota al
     * detectar la visualizacion de las ultimas en la vista para saber si
     * traer nuevas
     */
    fun getNewMovies() {
        Log.d(TRACK_INFO, "VM: get new movies $page")
        viewModelScope.launch {
            when (val result = getUseCaseMovies.getMoviesByPage(page)) {
                is DataResult.Error -> Log.d(TRACK_INFO, "error: " + result.exception.message)
                is DataResult.Success -> {
                    page = result.data.first().page+1
                    _movies.postValue(_movies.value?.plus(result.data))
                }
            }
        }
    }

    /**
     * Obtener pelicula por identificador
     */
    fun getMovieById(id: Int) {
        Log.d(TRACK_INFO, "VM: get movie id $id")
        viewModelScope.launch {
            _load.value = true
            _currentMovie.value = getUseCaseMovies.getMovieById(id)
            _load.value = false
        }
    }
}