package com.dfl.cleanarchmovieapp.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.domain.usecase.GetMovies
import com.dfl.cleanarchmovieapp.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun getAllMovies() {
        viewModelScope.launch {
            _load.value = true

            when (val result = getUseCaseMovies.getAllMovies()) {
                is DataResult.Error -> {
                    _load.value = false
                    Log.d("test", "error")
                }
                is DataResult.Success -> {
                    _load.value = false
                    Log.d("test", "nothing")
                    _movies.value = result.data
                }
            }
        }
    }
}