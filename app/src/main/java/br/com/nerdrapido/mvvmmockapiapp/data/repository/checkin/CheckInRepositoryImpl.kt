package br.com.nerdrapido.mvvmmockapiapp.data.repository.checkin

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.checkIn.CheckInDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.remote.network.NetworkController
import br.com.nerdrapido.mvvmmockapiapp.remote.service.CheckInService

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class CheckInRepositoryImpl(
    networkController: NetworkController,
    private val checkInDataMapper: CheckInDataMapper
) : CheckInRepository {

    private val service = networkController.retrofit.create(CheckInService::class.java)

    override suspend fun postCheckIn(checkInData: CheckInData) : DataWrapper<Boolean> {
        return safeApiCall {
            service.postCheckIn(checkInDataMapper.mapDataToRemote(checkInData))
            return@safeApiCall true
        }
    }
}
