package com.dfl.cleanarchmovieapp.data.local.dbsqlite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val posterPath: String?,
    val isAdult: Boolean
)