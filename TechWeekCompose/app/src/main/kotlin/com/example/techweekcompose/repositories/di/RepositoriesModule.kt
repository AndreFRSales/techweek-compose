package com.example.techweekcompose.di

import com.example.techweekcompose.repositories.PokemonRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { PokemonRepository(get()) }
}