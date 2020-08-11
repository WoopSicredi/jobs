package br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn

import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.base.BaseUseCaseInput

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
data class PostEventCheckInUseCaseInput(
    val checkIn : CheckInData
) : BaseUseCaseInput