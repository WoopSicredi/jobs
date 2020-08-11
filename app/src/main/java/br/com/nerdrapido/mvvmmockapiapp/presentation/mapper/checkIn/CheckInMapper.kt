package br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.checkIn

import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.base.Mapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.CheckIn

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
interface CheckInMapper : Mapper<CheckInData, CheckIn> {

    fun mapModelToData(model: CheckIn): CheckInData

    fun mapModelToDataList(dataList: List<CheckIn>): List<CheckInData> {
        val output = mutableListOf<CheckInData>()
        dataList.forEach {
            output.add(mapModelToData(it))
        }
        return output
    }
}