package com.example.techweekcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.techweekcompose.navigation.Navigation
import com.example.techweekcompose.theme.PokemonTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                Navigation()
            }
        }
    }
}

