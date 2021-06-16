package com.dfl.cleanarchmovieapp.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.domain.usecase.GetMovies
import com.dfl.cleanarchmovieapp.utils.Constants.TRACK_INFO
import com.dfl.cleanarchmovieapp.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagementMoviesVM @Inject constructor(
    private val getUseCaseMovies: GetMovies
) : ViewModel() {

    private val _load = MutableLiveData<Boolean>()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() {
            return _movies
        }
    private val _currentMovie = MutableLiveData<Movie>()
    val currentMovie: LiveData<Movie>
        get() {
            return _currentMovie
        }
    private var page: Int = 2

    /**
     * Obtener todas las peliculas
     */
    fun getAllMovies() {
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
    fun getNewMovies(visibleNumber: Int) {
        val fileBeforeCharge = 6
        val itemVisibleForCharge = (_movies.value?.count() ?: 0) - fileBeforeCharge
        //si faltan como minimo 6 peliculas por mostrar carga nuevas
        if (itemVisibleForCharge <= visibleNumber && _load.value == false) {
            _load.value = true
            viewModelScope.launch {
                when (val result = getUseCaseMovies.getMoviesByPage(page)) {
                    is DataResult.Error -> Log.d(TRACK_INFO, "error: " + result.exception.message)
                    is DataResult.Success -> {
                        page = result.data.first().page
                        _movies.value = _movies.value?.plus(result.data)
                    }
                }
                _load.value = false
            }
        }
    }

    /**
     * Obtener pelicula por identificador
     */
    fun getMovieById(id: Int) {
        viewModelScope.launch {
            _load.value = true
            _currentMovie.value = getUseCaseMovies.getMovieById(id)
            _load.value = false
        }
    }
}