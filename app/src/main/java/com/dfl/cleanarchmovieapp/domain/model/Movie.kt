package com.dfl.cleanarchmovieapp.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val posterPath: String?,
    val isAdult: Boolean = false
)
