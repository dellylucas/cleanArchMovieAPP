package com.dfl.cleanarchmovieapp.di

import android.app.Application
import androidx.room.Room
import com.dfl.cleanarchmovieapp.BuildConfig
import com.dfl.datamodule.IDataSourceLocal
import com.dfl.datamodule.IDataSourceRemote
import com.dfl.datamodule.dbsqlite.DataBase
import com.dfl.datamodule.dbsqlite.RoomDBDataSource
import com.dfl.datamodule.remote.ServerDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(): String = BuildConfig.API_KEY

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        DataBase::class.java,
        "movie_db"
    ).build()

    @Provides
    @Named("remote")
    fun remoteDataSourceProvider(
        @Named("apiKey") key: String
    ): IDataSourceRemote = ServerDataSource(key)

    @Provides
    @Named("local")
    fun roomDataSourceProvider(db: DataBase): IDataSourceLocal = RoomDBDataSource(db)
}