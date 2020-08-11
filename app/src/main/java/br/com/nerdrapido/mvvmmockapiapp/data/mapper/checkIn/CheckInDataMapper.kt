package br.com.nerdrapido.mvvmmockapiapp.data.mapper.checkIn

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.base.DataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.CheckInPost

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
interface CheckInDataMapper : DataMapper<CheckInData, CheckInPost> {

    fun mapDataToRemote(data: CheckInData): CheckInPost

    fun mapDataToRemoteList(dataList: List<CheckInData>): List<CheckInPost> {
        val output = mutableListOf<CheckInPost>()
        dataList.forEach {
            output.add(mapDataToRemote(it))
        }
        return output
    }
}