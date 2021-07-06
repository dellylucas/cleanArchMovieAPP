package com.dfl.datamodule.dbsqlite

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    fun getAllMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun findById(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<MovieEntity>)

}