package com.example.techweekcompose

import android.app.Application
import com.example.techweekcompose.detail.di.detailModule
import com.example.techweekcompose.repositories.di.repositoriesModule
import com.example.techweekcompose.main.di.mainModule
import com.example.techweekcompose.network.retrofitModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class TechWeekComposeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoinGraph()
    }

    private fun loadKoinGraph() {
        startKoin {
            loadKoinModules(listOf(repositoriesModule, retrofitModule, mainModule, detailModule))
        }
    }
}