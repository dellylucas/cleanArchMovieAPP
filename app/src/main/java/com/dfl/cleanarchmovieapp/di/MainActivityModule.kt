package com.dfl.cleanarchmovieapp.di

import com.dfl.cleanarchmovieapp.data.MoviesRepository
import com.dfl.cleanarchmovieapp.domain.usecase.GetMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    @ViewModelScoped
    fun getPopularMoviesProvider(moviesRepository: MoviesRepository) =
        GetMovies(moviesRepository)
}