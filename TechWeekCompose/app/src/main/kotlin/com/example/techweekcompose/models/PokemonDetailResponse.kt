package com.example.techweekcompose.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val id: Long,
    val weight: Int,
    val height: Int,
    val sprites: PokemonSprite
)