package br.com.nerdrapido.mvvmmockapiapp.data.mapper

import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.EventResponse

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
interface EventDataMaper  : DataMapper<EventData, EventResponse> {
}