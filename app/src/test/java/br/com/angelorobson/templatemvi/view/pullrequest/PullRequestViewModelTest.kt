package br.com.angelorobson.templatemvi.view.pullrequest

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Before
import org.junit.Test

class PullRequestViewModelTest {

    private lateinit var updateSpec: UpdateSpec<PullRequestModel, PullRequestEvent, PullRequestEffect>


    @Before
    fun setUp() {
        updateSpec = UpdateSpec(::pullRequestUpdate)
    }

    @Test
    fun initial_observePullRequestEffectDispatched() {
        val model = PullRequestModel()
        val repository = RepositoryBuilder.Builder().oneRepository().build()

        updateSpec
                .given(model)
                .whenEvent(InitialEvent(repository))
                .then(
                        assertThatNext<PullRequestModel, PullRequestEffect>(
                                hasNoModel(),
                                hasEffects(ObservePullRequestEffect(repository))

                        )
                )
    }

    @Test
    fun pullRequestLoadedEvent_pullRequestLoadedUpdated() {
        val model = PullRequestModel()
        val pullRequest = PullRequestBuilder.Builder().onePullRequest().build()
        val list = listOf(pullRequest, pullRequest)

        updateSpec
                .given(model)
                .whenEvent(PullRequestLoadedEvent(list))
                .then(
                        assertThatNext<PullRequestModel, PullRequestEffect>(
                                hasModel(
                                        model.copy(
                                                pullRequestResult = PullRequestResult.PullRequestLoaded(
                                                        pullRequests = list
                                                )
                                        )
                                ),
                                hasNoEffects()

                        )
                )
    }

    @Test
    fun pullRequestExceptionEvent_ErrorUpdated() {
        val model = PullRequestModel()
        val errorMessage = "error"

        updateSpec
                .given(model)
                .whenEvent(PullRequestExceptionEvent(errorMessage = errorMessage))
                .then(
                        assertThatNext<PullRequestModel, PullRequestEffect>(
                                hasModel(
                                        model.copy(
                                                pullRequestResult = PullRequestResult.Error(
                                                        errorMessage = errorMessage
                                                )
                                        )
                                ),
                                hasNoEffects()
                        )
                )
    }


    @Test
    fun pullRequestClickedEvent_pullRequestClickedEffectDispatched() {
        val model = PullRequestModel()
        val pullRequest = PullRequestBuilder.Builder().onePullRequest().onePullRequest().build()

        updateSpec
                .given(model)
                .whenEvent(PullRequestClickedEvent(pullRequest = pullRequest))
                .then(
                        assertThatNext<PullRequestModel, PullRequestEffect>(
                                hasNoModel(),
                                hasEffects(PullRequestClickedEffect(pullRequest))

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
