package com.dfl.cleanarchmovieapp.di.modules

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.data.MoviesRepository
import com.dfl.cleanarchmovieapp.domain.usecase.UseCaseMovies
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule {

    @Provides
    fun moviesRepositoryProvider(
        @Named("local")
        localDataSource: IDataSource,
        @Named("remote")
        remoteDataSource: IDataSource
    ) = MoviesRepository(localDataSource, remoteDataSource)

    @Provides
    fun useCaseMoviesProvider(
        repo: MoviesRepository
    ) = UseCaseMovies(repo)

}