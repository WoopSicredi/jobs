package com.example.appteste.network

import com.example.appteste.exception.FailureResponseException
import com.example.appteste.util.LiveDataResult
import retrofit2.Response

object DefaultNetwork {

    fun <T> request(response: Response<T>, tag: String): LiveDataResult<T> {
        return try {
            LiveDataResult.loading<T>()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                LiveDataResult.success(body)
            } else {
                LiveDataResult.error(FailureResponseException())
            }
        } catch (e:Exception) {
            LiveDataResult.error(e)
        }
    }
}