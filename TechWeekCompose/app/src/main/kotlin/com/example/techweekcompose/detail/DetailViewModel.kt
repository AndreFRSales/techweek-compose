package com.example.techweekcompose.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techweekcompose.repositories.PokemonRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailViewModel(
    private val pokemonUrl: String,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState: MutableState<UIState> = mutableStateOf(UIState.Loading)
    val uiState: State<UIState>
        get() = _uiState

    init {
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            try {
                pokemonRepository.fetchPokemon(pokemonUrl).run {
                    val pokemonData = PokemonData(weight = weight, height = height)
                    _uiState.value = UIState.Result(pokemonData)
                }
            } catch (exception: Exception) {
                _uiState.value = UIState.Error
            }
        }
    }
}

data class PokemonData(val weight: Int, val height: Int)

sealed class UIState {
    object Loading : UIState()
    data class Result(val pokemonData: PokemonData) : UIState()
    object Error : UIState()
}
