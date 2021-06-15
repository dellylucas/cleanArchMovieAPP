package com.dfl.cleanarchmovieapp.di

import android.app.Application
import androidx.room.Room
import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.data.local.FakeLocalProvider
import com.dfl.cleanarchmovieapp.data.local.dbsqlite.DataBase
import com.dfl.cleanarchmovieapp.data.local.dbsqlite.RoomDBDataSource
import com.dfl.cleanarchmovieapp.data.remote.ServerDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    /*  @Provides
      @Singleton
      @Named("apiKey")
      fun apiKeyProvider(app: Application): String = app.getString(R.string.api_key)
  */
    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        DataBase::class.java,
        "movie_db"
    ).build()

    @Provides
    @Named("fakeLocal")
    fun fakeDataSourceProvider(): IDataSource = FakeLocalProvider()

    @Provides
    @Named("remote")
    fun remoteDataSourceProvider(): IDataSource = ServerDataSource()

    @Provides
    @Named("local")
    fun roomDataSourceProvider(db: DataBase): IDataSource = RoomDBDataSource(db)
}