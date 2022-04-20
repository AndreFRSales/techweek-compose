package com.example.techweekcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.techweekcompose.detail.DetailScreen
import com.example.techweekcompose.extensions.encode
import com.example.techweekcompose.main.composables.MainScreen
import com.example.techweekcompose.navigation.Navigation.DETAILS_SCREEN_PATH
import com.example.techweekcompose.navigation.Navigation.IMAGE_URL_KEY
import com.example.techweekcompose.navigation.Navigation.POKEMON_NAME_KEY
import com.example.techweekcompose.navigation.Navigation.URL_KEY

object Navigation {
    const val URL_KEY = "url"
    const val IMAGE_URL_KEY = "imageUrl"
    const val POKEMON_NAME_KEY = "pokemonName"
    const val DETAILS_SCREEN_PATH =
        "/$URL_KEY={$URL_KEY}&$IMAGE_URL_KEY={$IMAGE_URL_KEY}&$POKEMON_NAME_KEY={$POKEMON_NAME_KEY}"
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(Screens.DetailScreen.route + DETAILS_SCREEN_PATH, arguments = listOf(
            navArgument(URL_KEY) {
                type = NavType.StringType
            },
            navArgument(IMAGE_URL_KEY) {
                type = NavType.StringType
            },
            navArgument(POKEMON_NAME_KEY) {
                type = NavType.StringType
            }
        )) { entry ->
            val url = entry.arguments?.getString(URL_KEY).orEmpty()
            val imageUrl = entry.arguments?.getString(IMAGE_URL_KEY).orEmpty()
            val pokemonName = entry.arguments?.getString(POKEMON_NAME_KEY).orEmpty()
            DetailScreen(navController = navController, url, imageUrl, pokemonName)
        }
    }
}

object DestinationsFactory {
    fun createDetailScreenDestination(url: String, urlImage: String, pokemonName: String) =
        Screens.DetailScreen.route +
                "/$URL_KEY=${url.encode()}&$IMAGE_URL_KEY=${urlImage.encode()}&$POKEMON_NAME_KEY=$pokemonName"

}