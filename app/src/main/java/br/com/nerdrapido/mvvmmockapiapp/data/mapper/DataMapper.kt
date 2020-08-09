package br.com.nerdrapido.mvvmmockapiapp.data.mapper

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
interface DataMapper<D, R> {

    /**
     * Maopeia um model R do remote para D da Data
     */
    fun mapRemoteToData(response: R): D

    fun mapRemoteToDataList(response: List<R>): List<D> {
        val output = mutableListOf<D>()
        response.forEach {
            output.add(mapRemoteToData(it))
        }
        return output
    }
}