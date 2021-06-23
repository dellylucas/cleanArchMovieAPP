package com.dfl.cleanarchmovieapp.di.modules

import com.dfl.cleanarchmovieapp.domain.usecase.UseCaseMovies
import com.dfl.cleanarchmovieapp.presentation.vm.ManagementMoviesVM
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class ListFragmentModule {

    @Provides
    fun mainViewModelProvider(getPopularMovies: UseCaseMovies) =
        ManagementMoviesVM(getPopularMovies)

}

@Subcomponent(modules = [(ListFragmentModule::class)])
interface ListFragmentComponent {
    val mainViewModel: ManagementMoviesVM
}