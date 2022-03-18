package com.example.techweekcompose.detail.di

import com.example.techweekcompose.detail.DetailViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModelOf

val detailModule = module {
    viewModelOf(::DetailViewModel)
}