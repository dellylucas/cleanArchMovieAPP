package com.dfl.cleanarchmovieapp.presentation.vm

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dfl.cleanarchmovieapp.data.FakeLocalProvider
import com.dfl.model.Movie
import com.dfl.cleanarchmovieapp.testutils.getOrAwaitValueTest
import com.dfl.datamodule.MoviesRepository
import com.dfl.usecasesmodule.GetMovies
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockkStatic
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

    private val movieTest=Movie(1, "osos", "", "")
    private val movieTwoTest=Movie(2, "perro", "", "")

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: ManagementMoviesVM

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {

        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0

        Dispatchers.setMain(dispatcher)
        viewModel = ManagementMoviesVM(
            GetMovies(
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
        assertThat(value.first()).isEqualTo(movieTest)
    }

    /**
     * @see FakeLocalProvider
     */
    @Test
    fun testIntegrationReturnMovieById() {
        val movieId = 2
        viewModel.getMovieById(movieId)

        val value = viewModel.currentMovie.getOrAwaitValueTest()
        assertThat(value).isEqualTo(movieTwoTest)
        assertThat(value).isNotEqualTo(movieTest)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}