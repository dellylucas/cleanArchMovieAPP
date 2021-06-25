package com.dfl.cleanarchmovieapp.testIDDagger

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dfl.cleanarchmovieapp.di.components.DaggerAppComponent
import com.dfl.cleanarchmovieapp.domain.model.Movie
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListMovieIntegrationTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val observer = mockk<Observer<List<Movie>>>()
   //private val component: TestComponent = Dagger DaggerAppComponent.factory().create()
   /* private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        vm = component.plus(MainActivityModule()).mainViewModel
        localDataSource = component.localDataSource as FakeLocalDataSource
        localDataSource.movies = defaultFakeMovies
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `data is loaded from server when local source is empty`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            vm.model.observeForever(observer)

            vm.onCoarsePermissionRequested()

            verify(observer).onChanged(
                ArgumentMatchers.refEq(MainViewModel.UiModel.Content(defaultFakeMovies))
            )
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `data is loaded from local source when available`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val fakeLocalMovies = listOf(mockedMovie.copy(10), mockedMovie.copy(11))
            val localDataSource = component.localDataSource as FakeLocalDataSource
            localDataSource.movies = fakeLocalMovies
            vm.model.observeForever(observer)

            vm.onCoarsePermissionRequested()

            verify(observer).onChanged(
                ArgumentMatchers.refEq(MainViewModel.UiModel.Content(fakeLocalMovies))
            )
        }*/
}