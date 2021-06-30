package com.dfl.datamodule.dbsqlite

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities =
    [MovieEntity::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}