package com.dfl.cleanarchmovieapp.di.modules

import android.app.Application
import androidx.room.Room
import com.dfl.cleanarchmovieapp.BuildConfig
import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.data.local.dbsqlite.DataBase
import com.dfl.cleanarchmovieapp.data.local.dbsqlite.RoomDBDataSource
import com.dfl.cleanarchmovieapp.data.remote.ServerDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
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
    ): IDataSource = ServerDataSource(key)

    @Provides
    @Named("local")
    fun roomDataSourceProvider(db: DataBase): IDataSource = RoomDBDataSource(db)
}