package com.example.techweekcompose.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSprite(val other: PokemonDreamWorld)

@Serializable
data class PokemonDreamWorld(@SerialName("dream_world") val dreamWorld: DreamWorld)

@Serializable
data class DreamWorld(@SerialName("front_default") val frontDefault: String)