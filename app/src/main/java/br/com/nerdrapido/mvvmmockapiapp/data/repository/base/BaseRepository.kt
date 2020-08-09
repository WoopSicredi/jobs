package br.com.nerdrapido.mvvmmockapiapp.data.repository.base

import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
interface BaseRepository {

    /**
     * Método para fazer uma chamada da api de forma segura e possibilitando tratamento de erros.
     *
     * @param call função de chamada da api.
     * @return Wrapper com o resultado.
     */
    suspend fun <T> safeApiCall(
        call: suspend () -> T
    ): DataWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                DataWrapper.Success(call.invoke())
            } catch (throwable: Throwable) {
                Timber.e(throwable)
                when (throwable) {
                    is IOException -> DataWrapper.NetworkError(throwable)
                    else -> DataWrapper.GenericError(throwable)
                }
            }
        }
    }
}
