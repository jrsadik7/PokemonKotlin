package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonDisplayer
import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonViewState
import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.ResultViewState
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.Action
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonPresenterTest {

    private val resultViewState1 = ResultViewState.create("name1", "http://name1.png")
    private val resultViewState2 = ResultViewState.create("name2", "http://name2.png")
    private val idleViewState = PokemonViewState.create(listOf(resultViewState1, resultViewState2))

    private val refreshAction: Action = mock()
    private val useCase: PokemonViewStateUseCase = mock()
    private val displayer: PokemonDisplayer = mock()

    private lateinit var presenter: PokemonPresenter

    @Before
    fun setUp() {
        whenever(useCase.loadFromNetwork()).thenReturn(Completable.fromAction(refreshAction))
        whenever(useCase.observeViewState()).thenReturn(Observable.just(idleViewState))
        presenter = PokemonPresenter(useCase, displayer)
    }

    @Test
    fun shouldShowConvertedViewStateWhenStartPresenting() {
        presenter.startPresenting()

        verify(displayer).display(idleViewState)
    }
}
