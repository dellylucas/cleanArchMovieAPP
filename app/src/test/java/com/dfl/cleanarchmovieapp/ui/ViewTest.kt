package com.dfl.cleanarchmovieapp.ui

import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.lifecycle.Lifecycle
import com.dfl.cleanarchmovieapp.presentation.ui.ListMovieFragment
import com.dfl.cleanarchmovieapp.presentation.vm.ManagementMoviesVM
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
//@HiltAndroidTest
//@Config(application = MoviesApplication::class)
class ViewTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var scenario: FragmentScenario<ListMovieFragment>

    @Before
    fun setup() {
        val viewModel = mockk<ManagementMoviesVM>()
        scenario = launchFragmentInContainer(
        )
        // Tried implementing shadowOf as the error suggests.
        shadowOf(Looper.getMainLooper()).idle()
    }

    @Test
    fun shouldNotBeNull() {
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.withFragment {
            val recycler = binding.moviesRecyclerView
            // workaround robolectric recyclerView issue
            recycler.measure(0, 0)
            recycler.layout(0, 0, 100, 1000)

            // lets just imply a certain item at position 0 for simplicity
            recycler.findViewHolderForAdapterPosition(0)?.itemView?.performClick()
            Any()
        }

    }

}