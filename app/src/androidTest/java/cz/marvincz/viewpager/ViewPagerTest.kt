package cz.marvincz.viewpager

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, checking the function of [ViewPager2][androidx.viewpager2.widget.ViewPager2] and [FragmentStateAdapter][androidx.viewpager2.adapter.FragmentStateAdapter]
 */
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ViewPagerTest {
    /**
     * ViewPager with unmodified data.
     *
     * Check first page, swipe to second page and check it.
     */
    @Test
    fun test() {
        ActivityScenario.launch(MainActivity::class.java)

        checkPageShows("A")

        swipeLeft()

        checkPageShows("B")
    }

    /**
     * ViewPager with modified data. Page order should be reversed.
     *
     * Reorder data, check first page, swipe to second page and check it.
     */
    @Test
    fun testReorder() {
        ActivityScenario.launch(MainActivity::class.java)

        reorder()

        checkPageShows("B")

        swipeLeft()

        checkPageShows("A")
    }

    /**
     * ViewPager with modified data. Page order should be reversed.
     *
     * Swipe to second page, reorder data. Check second page, swipe back to first page and check it.
     */
    @Test
    fun testReorder2() {
        ActivityScenario.launch(MainActivity::class.java)

        swipeLeft()

        reorder()

        checkPageShows("A")

        swipeRight()

        checkPageShows("B")
    }

    private fun reorder() {
        onView(withId(R.id.fab))
            .perform(ViewActions.click())
    }

    private val viewPager: Matcher<View>
        get() = withId(R.id.pager)

    private fun swipeLeft() {
        onView(viewPager)
            .perform(ViewActions.swipeLeft())
    }

    private fun swipeRight() {
        onView(viewPager)
            .perform(ViewActions.swipeRight())
    }

    private fun checkPageShows(expected: String) {
        currentPage
            .check(
                matches(
                    hasDescendant(withText(expected))
                )
            )
    }

    private val currentPage: ViewInteraction
        get() = onView(
            allOf(
                withParent(viewPager),
                isDisplayingAtLeast(50)
            )
        )
}
