package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Pokemon
import `in`.co.kshitijjain.pokemonkotlin.rx.Converter

class PokemonViewStateConverter(private val resultViewStateConverter: ResultViewStateConverter)
    : Converter<Pokemon, PokemonViewState> {
    override fun apply(pokemon: Pokemon): PokemonViewState {
        val resultViewState = ArrayList<ResultViewState>()
        pokemon.mapTo(resultViewState, resultViewStateConverter::convert)
        return PokemonViewState.create(resultViewState)
    }
}