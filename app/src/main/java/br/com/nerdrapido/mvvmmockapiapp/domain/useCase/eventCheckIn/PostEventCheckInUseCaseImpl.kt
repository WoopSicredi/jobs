package br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn

import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.repository.checkin.CheckInRepository

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class PostEventCheckInUseCaseImpl(
    private val checkInRepository: CheckInRepository
) : PostEventCheckInUseCase {

    override suspend fun execute(input: PostEventCheckInUseCaseInput): DataWrapper<Boolean> {
        return checkInRepository.postCheckIn(input.checkIn)
    }
}