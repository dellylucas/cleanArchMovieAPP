package com.dfl.datamodule.dbsqlite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val posterPath: String?,
    val isAdult: Boolean,
    val page:Int
)