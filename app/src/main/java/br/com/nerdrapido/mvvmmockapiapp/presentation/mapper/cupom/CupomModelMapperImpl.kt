package br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.cupom

import br.com.nerdrapido.mvvmmockapiapp.data.model.CupomData
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Cupom

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class CupomModelMapperImpl : CupomModelMapper {
    override fun mapDataToModel(data: CupomData): Cupom {
        return Cupom(data.id, data.eventId, data.discount)
    }
}