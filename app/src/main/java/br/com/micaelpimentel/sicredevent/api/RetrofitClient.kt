package br.com.micaelpimentel.sicredevent.api

import br.com.micaelpimentel.sicredevent.BuildConfig
import br.com.micaelpimentel.sicredevent.api.service.CheckinService
import br.com.micaelpimentel.sicredevent.api.service.EventService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private fun setupHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            httpClient.writeTimeout(30, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
        }

        return httpClient.build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(setupHttpClient())
        .build()

    fun createEventService(): EventService = retrofit.create(EventService::class.java)

    fun createCheckinService(): CheckinService = retrofit.create(CheckinService::class.java)
}