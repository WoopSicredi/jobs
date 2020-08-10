package br.com.nerdrapido.mvvmmockapiapp.testShared

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class MockServiceInterceptorWithException(private val throwable: Throwable) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.v(chain.request().headers.toString())
        throw throwable
    }
}