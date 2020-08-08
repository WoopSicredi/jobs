package br.com.nerdrapido.mvvmmockapiapp.remote.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import timber.log.Timber
import java.io.InputStream


/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class MockServiceInterceptorWithFile(private val fileName: String, private val code: Int) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val classloader = Thread.currentThread().contextClassLoader
        val inputStream: InputStream = classloader.getResourceAsStream(fileName)
        val responseBody = inputStream.bufferedReader().use { it.readText() }
        Timber.d(responseBody)

        return Response.Builder()
            .code(code)
            .message(Gson().toJson(responseBody))
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(
                responseBody
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}