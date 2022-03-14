package com.example.techweekcompose.di

import com.example.techweekcompose.repositories.PokemonListRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { PokemonListRepository() }
}