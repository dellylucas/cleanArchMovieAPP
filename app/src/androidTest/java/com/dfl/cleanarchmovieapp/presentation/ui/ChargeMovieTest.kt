package com.dfl.cleanarchmovieapp.presentation.ui


import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.dfl.cleanarchmovieapp.R
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ChargeMovieTest {
    private lateinit var textMovie: String

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        textMovie = "Cruella"
    }

    @Test
    fun chargeMovieTest() {

        SystemClock.sleep(5000)
        val textView = onView(
            allOf(
                withId(R.id.movieTextView), withText(textMovie),
                withParent(withParent(withId(R.id.moviesRecyclerView))),
                isDisplayed()
            )
        )
        textView.check(matches(withText(textMovie)))
    }
}
