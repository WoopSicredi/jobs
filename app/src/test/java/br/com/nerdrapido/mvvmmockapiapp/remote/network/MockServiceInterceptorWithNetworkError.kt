package br.com.nerdrapido.mvvmmockapiapp.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class MockServiceInterceptorWithNetworkError : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.v(chain.request().headers.toString())
        throw IOException()
    }
}