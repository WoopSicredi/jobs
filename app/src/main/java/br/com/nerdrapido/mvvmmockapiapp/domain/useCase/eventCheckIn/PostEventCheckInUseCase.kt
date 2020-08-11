package br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn

import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.base.BaseUseCase

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
interface PostEventCheckInUseCase :
    BaseUseCase<PostEventCheckInUseCaseInput, DataWrapper<Boolean>>