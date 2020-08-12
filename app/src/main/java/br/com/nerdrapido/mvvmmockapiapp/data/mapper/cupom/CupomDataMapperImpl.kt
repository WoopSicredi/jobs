package br.com.nerdrapido.mvvmmockapiapp.data.mapper.cupom

import br.com.nerdrapido.mvvmmockapiapp.data.model.CupomData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.CupomResponse

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class CupomDataMapperImpl :
    CupomDataMapper {
    override fun mapRemoteToData(response: CupomResponse): CupomData {
        return CupomData(response.id, response.eventId, response.discount)
    }
}