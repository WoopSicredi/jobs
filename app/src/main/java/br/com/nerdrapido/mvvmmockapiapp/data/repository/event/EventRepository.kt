package br.com.nerdrapido.mvvmmockapiapp.data.repository.event

import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.data.repository.base.BaseRepository

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
interface EventRepository : BaseRepository {

    suspend fun getEventList() : DataWrapper<List<EventData>>

}