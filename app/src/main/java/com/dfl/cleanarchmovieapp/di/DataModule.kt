package com.dfl.cleanarchmovieapp.di

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.data.MoviesRepository
import com.dfl.cleanarchmovieapp.data.local.FakeLocalProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun moviesRepositoryProvider(
        localDataSource: IDataSource
    ) = MoviesRepository(localDataSource)

    @Provides
    fun fakeDataSourceProvider(): IDataSource = FakeLocalProvider()
}