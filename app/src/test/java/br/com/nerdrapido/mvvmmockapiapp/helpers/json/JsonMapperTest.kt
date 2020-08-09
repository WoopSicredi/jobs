package br.com.nerdrapido.mvvmmockapiapp.helpers.json

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import com.google.gson.reflect.TypeToken
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import java.lang.reflect.Type

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class JsonMapperTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val jsonMapper: JsonMapper by inject()

    val val1 = "abcdefg"
    val val2 = 10
    val jsonString = "{\"val1\":\"$val1\",\"val2\":$val2}"
    val jsonMapperTestObject = JsonMapperTestObject(val1, val2)

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
    fun `test fromString with type`() {
        val type: Type = object : TypeToken<JsonMapperTestObject>() {}.type
        val jsonObject : JsonMapperTestObject = jsonMapper.fromString(jsonString, type)
        assert(jsonObject.val1 == val1)
        assert(jsonObject.val2 == val2)
    }

    @Test
    fun `test toString`() {
        val json = jsonMapper.toString(jsonMapperTestObject)
        val jsonObject = jsonMapper.fromString(json, JsonMapperTestObject::class.java)
        assert(jsonObject.val1 == val1)
        assert(jsonObject.val2 == val2)
    }


    data class JsonMapperTestObject(
        val val1: String,
        val val2: Int
    )
}