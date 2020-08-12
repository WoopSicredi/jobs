package br.com.nerdrapido.mvvmmockapiapp.data.mapper.cupom

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.base.DataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.CupomData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.CupomResponse

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
interface CupomDataMapper :
    DataMapper<CupomData, CupomResponse>