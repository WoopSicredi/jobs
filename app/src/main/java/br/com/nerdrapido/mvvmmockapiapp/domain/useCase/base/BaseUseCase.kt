package br.com.nerdrapido.mvvmmockapiapp.domain.useCase.base;

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 *
 * [I] input
 * [O] output
 */
interface BaseUseCase<I : BaseUseCaseInput, O> {

    /**
     * Executa o use case.
     */
    suspend fun execute(input: I): O
}
