package br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.base

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
interface Mapper<D, M> {

    /**
     * Maopeia um model R do remote para D da Data
     */
    fun mapDataToModel(data: D): M

    fun mapDataToModelList(dataList: List<D>): List<M> {
        val output = mutableListOf<M>()
        dataList.forEach {
            output.add(mapDataToModel(it))
        }
        return output
    }
}