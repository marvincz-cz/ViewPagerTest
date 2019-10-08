package cz.marvincz.viewpager

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ViewPagerTest {
    @Test
    fun test() {
        ActivityScenario.launch(MainActivity::class.java)

        checkPage("A")

        swipeLeft()

        checkPage("B")

        swipeRight()

        checkPage("A")
    }

    @Test
    fun testReorder() {
        ActivityScenario.launch(MainActivity::class.java)

        reorder()

        checkPage("B")

        swipeLeft()

        checkPage("A")

        swipeRight()

        checkPage("B")
    }

    @Test
    fun testReorder2() {
        ActivityScenario.launch(MainActivity::class.java)

        swipeLeft()

        reorder()

        checkPage("A")

        swipeRight()

        checkPage("B")

        swipeLeft()

        checkPage("A")
    }

    private fun reorder() {
        onView(withId(R.id.fab))
            .perform(ViewActions.click())
    }

    private fun viewPager() = withId(R.id.pager)

    private fun swipeLeft() {
        onView(viewPager())
            .perform(ViewActions.swipeLeft())
    }

    private fun swipeRight() {
        onView(viewPager())
            .perform(ViewActions.swipeRight())
    }

    private fun checkPage(text: String) {
        currentPage()
            .check(
                matches(
                    hasDescendant(
                        withText(text)
                    )
                )
            )
    }

    private fun currentPage(): ViewInteraction {
        return onView(
            allOf(
                withParent(viewPager()),
                isDisplayingAtLeast(50)
            )
        )
    }
}
