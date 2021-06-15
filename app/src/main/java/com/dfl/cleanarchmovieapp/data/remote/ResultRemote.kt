package com.dfl.cleanarchmovieapp.data.remote

import com.google.gson.annotations.SerializedName

data class ResultRemote(
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<MovieRemote>
)

data class MovieRemote(
    val id: Int,
    val title: String,
    val adult: Boolean,
    val overview: String?,
    @SerializedName("genre_ids")
    val genreId: List<Int>,
    @SerializedName("poster_path")
    val posterPath: String
)