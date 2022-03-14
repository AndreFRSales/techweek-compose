package com.example.techweekcompose.network

import com.example.techweekcompose.models.PokemonDetailResponse
import com.example.techweekcompose.models.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon/")
    suspend fun fetchList(@Query("limit") limit: Int, @Query("offset") offset: Int) : PokemonListResponse

    @GET
    suspend fun fetchPokemon(@Url url: String) : PokemonDetailResponse
}