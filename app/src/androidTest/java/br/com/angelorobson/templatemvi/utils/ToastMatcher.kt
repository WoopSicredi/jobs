package br.com.angelorobson.templatemvi.utils

import android.os.IBinder

import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


class ToastMatcher : TypeSafeMatcher<Root?>() {
    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }

    override fun matchesSafely(item: Root?): Boolean {
        val type: Int = item!!.windowLayoutParams!!.get().type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowToken: IBinder? = item?.decorView?.windowToken
            val appToken: IBinder? = item?.decorView?.applicationWindowToken
            if (windowToken === appToken) {
                // windowToken == appToken means this window isn't contained by any other windows.
                // if it was a window for an activity, it would have TYPE_BASE_APPLICATION.
                return true
            }
        }
        return false
    }
}

fun isToast(): Matcher<Root?>? {
    return ToastMatcher()
}