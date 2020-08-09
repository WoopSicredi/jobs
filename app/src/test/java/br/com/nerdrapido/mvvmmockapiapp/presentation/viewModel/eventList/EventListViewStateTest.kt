package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.eventList

import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class EventListViewStateTest {

    @Test
    fun `test EventListViewStateTest getters`() {
        val itemResponse = EventListViewState()
        Assert.assertEquals(ViewStateEnum.LOADING, itemResponse.state)
        Assert.assertNull(itemResponse.error)
        Assert.assertEquals(0, itemResponse.eventList.count())
    }
}