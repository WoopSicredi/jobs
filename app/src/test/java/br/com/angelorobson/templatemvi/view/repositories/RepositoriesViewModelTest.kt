package br.com.angelorobson.templatemvi.view.repositories

import androidx.paging.PagedList
import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RepositoriesViewModelTest {

    private lateinit var updateSpec: UpdateSpec<RepositoriesModel, RepositoriesEvent, RepositoriesEffect>


    @Before
    fun setUp() {
        updateSpec = UpdateSpec(::repositoriesUpdate)
    }

    @Test
    fun initial_observerRepositoriesEffectDispatched() {
        val model = RepositoriesModel()
        updateSpec
                .given(model)
                .whenEvent(InitialEvent)
                .then(
                        assertThatNext<RepositoriesModel, RepositoriesEffect>(
                                hasNoModel(),
                                hasEffects(ObserveRepositoriesEffect)

                        )
                )
    }

    @Test
    fun repositoriesLoadedEvent_RepositoriesLoadedUpdated() {
        val model = RepositoriesModel()
        val compositeDisposable = CompositeDisposable()
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<Repository>
        val hasError = false
        val isLoading = true

        updateSpec
                .given(model)
                .whenEvent(RepositoriesLoadedEvent(
                        repositories = pagedList,
                        hasError = hasError,
                        compositeDisposable = compositeDisposable,
                        isLoading = isLoading

                ))
                .then(
                        assertThatNext<RepositoriesModel, RepositoriesEffect>(
                                hasNoEffects(),
                                hasModel(
                                        model.copy(
                                                repositoryResult = RepositoriesResult.RepositoriesLoaded(
                                                        repositories = pagedList,
                                                        isLoading = isLoading,
                                                        compositeDisposable = compositeDisposable,
                                                        hasError = hasError
                                                )
                                        )
                                )

                        )
                )
    }


    @Test
    fun repositoriesExceptionEvent_ErrorUpdated() {
        val model = RepositoriesModel()
        val errorMessage = "error"
        updateSpec
                .given(model)
                .whenEvent(RepositoriesExceptionEvent(errorMessage = errorMessage))
                .then(
                        assertThatNext<RepositoriesModel, RepositoriesEffect>(
                                hasModel(
                                        model.copy(
                                                repositoryResult = RepositoriesResult.Error(
                                                        errorMessage = errorMessage
                                                )
                                        )
                                ),
                                hasNoEffects()
                        )
                )
    }

    @Test
    fun repositoryClickedEvent_repositoryClickedEffectDispatched() {
        val model = RepositoriesModel()
        val repository = RepositoryBuilder.Builder().oneRepository().oneRepository().build()

        updateSpec
                .given(model)
                .whenEvent(RepositoryClickedEvent(repository))
                .then(
                        assertThatNext<RepositoriesModel, RepositoriesEffect>(
                                hasNoModel(),
                                hasEffects(RepositoryClickedEffect(repository))

                        )
                )
    }

    @Test
    fun retryEvent_observeRetryEffectDispatched() {
        val model = RepositoriesModel()

        updateSpec
                .given(model)
                .whenEvent(RetryEvent)
                .then(
                        assertThatNext<RepositoriesModel, RepositoriesEffect>(
                                hasNoModel(),
                                hasEffects(ObserveRetryEffect)

                        )
                )
    }


    /* fun <T> mockPagedList(list: List<T>): PagedList<T> {
         val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
         Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
             val index = invocation.arguments.first() as Int
             list[index]
         }
         Mockito.`when`(pagedList.size).thenReturn(list.size)
         return pagedList
     }*/
}
