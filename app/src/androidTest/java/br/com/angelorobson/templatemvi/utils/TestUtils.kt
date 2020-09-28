package br.com.angelorobson.templatemvi.utils

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.util.Checks
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.internal.matchers.TypeSafeMatcher

object TestUtils {
    fun waitEspresso(milles: Long) {
        try {
            Thread.sleep(milles)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun checkIfIdIsDisplayed(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkIfIdIsNotVisible(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    fun findAndClick(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click())
    }

    fun getResourceString(id: Int): String {
        val targetContext = ApplicationProvider.getApplicationContext<Context>()
        return targetContext.resources.getString(id)
    }

    fun getResourceStringWithParameters(id: Int, parameters: Int): String {
        val targetContext = ApplicationProvider.getApplicationContext<Context>()
        return targetContext.resources.getString(id, parameters)
    }
}