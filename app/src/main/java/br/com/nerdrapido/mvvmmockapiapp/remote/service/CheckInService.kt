package br.com.nerdrapido.mvvmmockapiapp.remote.service

import br.com.nerdrapido.mvvmmockapiapp.remote.model.CheckInPost
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
interface CheckInService {

    /**
     * http://5b840ba5db24a100142dcd8c.mockapi.io/api/checkin
     *
     * No Momento do desenvolviemnto o endpoint checkin estava fora,
     * por isso assumido sem retorno
     */
    @POST("checkin")
    suspend fun postCheckIn(@Body body: CheckInPost)

}