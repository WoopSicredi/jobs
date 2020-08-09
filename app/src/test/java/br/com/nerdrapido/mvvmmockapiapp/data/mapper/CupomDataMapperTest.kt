package br.com.nerdrapido.mvvmmockapiapp.data.mapper

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.cupom.CupomDataMapper
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.remote.model.CupomResponse
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class CupomDataMapperTest : KoinTest{

    val id = "3"
    val eventId = "3"
    val discount = 17

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val cupomDataMapper : CupomDataMapper by inject()

    @Before
    fun setUp() {
        startKoin {
            androidContext(context)
            modules(
                MainModule.module
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test mapp cupom from remote to data`() {
        val cupomResponse = CupomResponse(id, eventId, discount)
        val cupomData = cupomDataMapper.mapRemoteToData(cupomResponse)
        Assert.assertEquals(id, cupomData.id)
        Assert.assertEquals(eventId, cupomData.eventId)
        Assert.assertEquals(discount, cupomData.discount)
    }
}