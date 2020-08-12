package br.com.nerdrapido.mvvmmockapiapp.data.mapper.event

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.base.DataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.EventResponse

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
interface EventDataMapper  :
    DataMapper<EventData, EventResponse>