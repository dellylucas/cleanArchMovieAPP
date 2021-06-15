package com.dfl.cleanarchmovieapp.di

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.data.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun moviesRepositoryProvider(
        @Named("local")
        localDataSource: IDataSource,
        @Named("remote")
        remoteDataSource: IDataSource
    ) = MoviesRepository(localDataSource, remoteDataSource)

}