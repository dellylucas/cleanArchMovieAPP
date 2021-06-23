package com.dfl.cleanarchmovieapp.presentation.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dfl.cleanarchmovieapp.data.FakeLocalProvider
import com.dfl.cleanarchmovieapp.data.MoviesRepository
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.domain.usecase.UseCaseMovies
import com.dfl.cleanarchmovieapp.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Prueba de integracion  entre modulos
 * viewModel/repositorio / dataSourceFAKETest
 */
class ManagementMoviesVMTest {

    @get:Rule
    var instantRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: ManagementMoviesVM

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = ManagementMoviesVM(
            UseCaseMovies(
                MoviesRepository(
                    FakeLocalProvider(),
                    FakeLocalProvider()
                )
            )
        )
    }

    @Test
    fun testIntegrationReturnMovies() {
        viewModel.getAllMovies()

        val value = viewModel.movies.getOrAwaitValueTest()
        assertThat(value.first()).isEqualTo(Movie(1, "osos", "", ""))
    }

    @Test
    fun testIntegrationReturnMovieById() {
        val movieId = 2
        viewModel.getMovieById(movieId)

        val value = viewModel.currentMovie.getOrAwaitValueTest()
        assertThat(value).isEqualTo(Movie(2, "perro", "", ""))
        assertThat(value).isNotEqualTo(Movie(1, "osos", "", ""))
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}