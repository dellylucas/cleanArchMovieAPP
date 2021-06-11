package com.dfl.cleanarchmovieapp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfl.cleanarchmovieapp.domain.usecase.GetMovies
import com.dfl.cleanarchmovieapp.presentation.ui.UiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagementMoviesVM @Inject constructor(
    private val getUseCaseMovies: GetMovies
) : ViewModel() {

    private val _movies = MutableLiveData<UiModel>()
    val movies: LiveData<UiModel>
        get() {
            return _movies
        }
   fun getAllMovies(){
       viewModelScope.launch {
           _movies.value = UiModel.Loading
           _movies.value = UiModel.ContentMovies (getUseCaseMovies.getAllMovies())
       }
   }
}