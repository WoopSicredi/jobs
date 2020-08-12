package br.com.nerdrapido.mvvmmockapiapp.data.repository.checkin

import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.repository.base.BaseRepository

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
interface CheckInRepository : BaseRepository {

    suspend fun postCheckIn(checkInData: CheckInData): DataWrapper<Boolean>

}