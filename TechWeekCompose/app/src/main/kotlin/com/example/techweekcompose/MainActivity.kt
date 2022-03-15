package com.example.techweekcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.techweekcompose.network.PokemonService
import com.example.techweekcompose.theme.PokemonTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    Box(
        modifier = Modifier.background(color = PokemonTheme.colors.error)
        ) {
        Text(text = "Hello World", style = PokemonTheme.typography.h1)
    }

}