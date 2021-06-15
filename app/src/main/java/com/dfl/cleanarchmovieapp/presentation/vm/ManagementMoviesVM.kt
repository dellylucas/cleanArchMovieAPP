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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagementMoviesVM @Inject constructor(
    private val getUseCaseMovies: GetMovies
) : ViewModel() {

    private val _load = MutableLiveData<Boolean>()
    val load: LiveData<Boolean>
        get() {
            return _load
        }

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

    fun getMovieById(id: Int) {
        viewModelScope.launch {
            _load.value = true
            _currentMovie.value = getUseCaseMovies.getMovieById(id)
            _load.value = false
        }
    }
}