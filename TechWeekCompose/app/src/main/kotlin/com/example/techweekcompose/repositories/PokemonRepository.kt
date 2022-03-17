package com.example.techweekcompose.repositories

import com.example.techweekcompose.network.PokemonService

class PokemonRepository(private val pokemonService: PokemonService) {

    suspend fun fetchPokemonList(limit: Int, offset: Int) = pokemonService.fetchList(limit, offset)
    suspend fun fetchPokemon(url: String) = pokemonService.fetchPokemon(url)

}