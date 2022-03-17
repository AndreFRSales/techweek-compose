package com.example.techweekcompose.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techweekcompose.extensions.getLimitAndOffsetValues
import com.example.techweekcompose.models.PokemonDomain
import com.example.techweekcompose.models.PokemonListResponse
import com.example.techweekcompose.repositories.PokemonRepository
import kotlinx.coroutines.launch

class MainViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _uiState: MutableState<UIState> = mutableStateOf(UIState.Loading)
    val uiState: State<UIState>
        get() = _uiState

    private var pokemonList = listOf<PokemonDomain>()

    init {
        fetchFirstPage()
    }

    private fun fetchFirstPage() {
        viewModelScope.launch {
            try {
                val pokemonResponseList = pokemonRepository.fetchPokemonList(20, 20)
                processPokemonData(pokemonResponseList)
            } catch (exception: Exception) {
                _uiState.value = UIState.GenericError
            }
        }
    }

    fun fetchNextPage(nextPage: String?) {
        viewModelScope.launch {
            nextPage?.let {
                val (limit, offset) = nextPage.getLimitAndOffsetValues()
                val response = pokemonRepository.fetchPokemonList(limit, offset)
                processPokemonData(response)
            }
        }
    }

    private fun processPokemonData(pokemonListResponse: PokemonListResponse) {
        viewModelScope.launch {
            with(pokemonListResponse) {
                if (results.isNotEmpty()) {
                    val pokemonListDomain = results.map { pokemon ->
                        try {
                            val pokemonResponse = pokemonRepository.fetchPokemon(pokemon.url)
                            PokemonDomain(
                                pokemon.name,
                                pokemon.url,
                                pokemonResponse.sprites.other.dreamWorld.frontDefault,
                            )
                        } catch (e: Exception) {
                            PokemonDomain("", "", "")
                        }
                    }
                    pokemonList = pokemonList + pokemonListDomain
                    _uiState.value = UIState.Result(pokemonList, nextPage = next, total = count)
                }
            }
        }
    }
}

sealed class UIState {
    object Loading : UIState()
    object GenericError : UIState()
    data class Result(val pokemonList: List<PokemonDomain>, val nextPage: String?, val total: Int) :
        UIState()
}
