package br.com.angelorobson.templatemvi.utils

import androidx.test.espresso.idling.CountingIdlingResource
import br.com.angelorobson.templatemvi.view.utils.IdlingResource


class TestIdlingResource : IdlingResource {

    var countingIdlingResource = CountingIdlingResource("IdlingResource")
    override fun increment() {
        countingIdlingResource.increment()
    }

    override fun decrement() {
        countingIdlingResource.decrement()
    }
}
