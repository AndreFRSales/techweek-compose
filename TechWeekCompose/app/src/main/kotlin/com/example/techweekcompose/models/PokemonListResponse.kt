package com.example.techweekcompose.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int,
    val next: String,
    val results: List<PokemonResponse>
)