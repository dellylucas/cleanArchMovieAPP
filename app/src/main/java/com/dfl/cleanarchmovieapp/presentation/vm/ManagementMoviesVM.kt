package com.dfl.cleanarchmovieapp.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dfl.model.Movie
import com.dfl.sharedmodule.Constants.TRACK_INFO
import com.dfl.sharedmodule.DataResult
import com.dfl.usecasesmodule.GetMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagementMoviesVM @Inject constructor(
    private val getUseCaseMovies: GetMovies
) : ViewModel() {

    private val _load = MutableLiveData<Boolean>()
    val load: LiveData<Boolean>
        get() = _load


    private val _currentMovie = MutableLiveData<Movie>()
    val currentMovie: LiveData<Movie>
        get() = _currentMovie

    /**
     * Obtener todas las peliculas
     */
    fun getAllMovies(): Flow<PagingData<Movie>> {
        Log.d(TRACK_INFO, "VM: get all movies")
        return getUseCaseMovies.getAllMovies()
            .cachedIn(viewModelScope)
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