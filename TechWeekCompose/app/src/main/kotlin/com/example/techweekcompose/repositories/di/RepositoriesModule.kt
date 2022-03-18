package com.example.techweekcompose.repositories.di

import com.example.techweekcompose.repositories.PokemonRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoriesModule = module {
    singleOf(::PokemonRepository)
}