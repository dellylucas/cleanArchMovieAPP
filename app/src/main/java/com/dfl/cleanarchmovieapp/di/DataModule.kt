package com.dfl.cleanarchmovieapp.di

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.data.MoviesRepository
import com.dfl.cleanarchmovieapp.data.local.FakeLocalProvider
import com.dfl.cleanarchmovieapp.data.remote.Server
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun moviesRepositoryProvider(
        @Named("remote")
        localDataSource: IDataSource
    ) = MoviesRepository(localDataSource)

    @Provides
    @Named("fakeLocal")
    fun fakeDataSourceProvider(): IDataSource = FakeLocalProvider()

    @Provides
    @Named("remote")
    fun remoteDataSourceProvider(): IDataSource = Server()
}