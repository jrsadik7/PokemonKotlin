package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Result
import retrofit2.Converter

class ResultViewStateConverter : Converter<Result, ResultViewState> {
    override fun convert(result: Result): ResultViewState {
        return ResultViewState.create(getCapitalizedNameFrom(result.name),
                getImageUrlFrom(result.url))
    }

    private fun getCapitalizedNameFrom(name: String): String {
        return name.substring(0, 1).toUpperCase() + name.substring(1)
    }

    private fun getImageUrlFrom(url: String): String {
        val urlParts = url.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val number = Integer.parseInt(urlParts[urlParts.size - 1])
        return "http://pokeapi.co/media/sprites/pokemon/$number.png"
    }
}