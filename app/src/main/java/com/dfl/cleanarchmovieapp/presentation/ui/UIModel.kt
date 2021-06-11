package com.dfl.cleanarchmovieapp.presentation.ui

import com.dfl.cleanarchmovieapp.domain.model.Movie

sealed class UiModel {
    object Loading : UiModel()
    data class ContentMovies(val movies: List<Movie>) : UiModel()
}