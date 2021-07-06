package com.dfl.cleanarchmovieapp.di

import com.dfl.datamodule.IDataSourceLocal
import com.dfl.datamodule.IDataSourceRemote
import com.dfl.datamodule.MoviesRepository
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
        localDataSource: IDataSourceLocal,
        @Named("remote")
        remoteDataSource: IDataSourceRemote
    ) = MoviesRepository(localDataSource, remoteDataSource)

}