package br.com.nerdrapido.mvvmmockapiapp.remote.network

import br.com.nerdrapido.mvvmmockapiapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class NetworkController(
    private val serviceInterceptor: Interceptor
) {

    val retrofit: Retrofit = Retrofit.Builder()
        .client(getInterceptor())
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Retorna o OkHttpClient.
     */
    private fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(serviceInterceptor)
            .addInterceptor(logging)
        return builder.build()
    }
}