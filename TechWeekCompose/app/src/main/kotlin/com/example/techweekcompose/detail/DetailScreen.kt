package com.example.techweekcompose.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.techweekcompose.R
import com.example.techweekcompose.common.composables.CustomLoading
import com.example.techweekcompose.common.composables.CustomToolbar
import com.example.techweekcompose.common.composables.GenericError
import com.example.techweekcompose.theme.PokemonTheme
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreen(navController: NavController, url: String, urlImage: String, pokemonName: String) {
    Scaffold(topBar = {
        CustomToolbar(text = pokemonName) {
            navController.navigateUp()
        }
    }) {
        DetailScreenContent(url = url, urlImage = urlImage, pokemonName = pokemonName)
    }
}

@Composable
fun DetailScreenContent(url: String, urlImage: String, pokemonName: String) {
    val detailViewModel: DetailViewModel by viewModel { parametersOf(url) }
    val uiState by detailViewModel.uiState

    when (uiState) {
        UIState.Error -> GenericError(
            title = stringResource(id = R.string.detail_generic_error_title),
            description = stringResource(
                id = R.string.detail_generic_error_description
            )
        )
        UIState.Loading -> CustomLoading()
        is UIState.Result -> DetailContent(urlImage = urlImage, pokemonName = pokemonName)
    }


}

@Composable
fun DetailContent(urlImage: String, pokemonName: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        val painter = rememberImagePainter(urlImage)
        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.main_icon_description, pokemonName),
            modifier = Modifier
                .padding(top = PokemonTheme.dimensions.medium)
                .align(Alignment.CenterHorizontally)
                .size(PokemonTheme.iconDimensions.large),
            contentScale = ContentScale.Fit,
        )
        Text(
            text = pokemonName,
            style = PokemonTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = PokemonTheme.dimensions.medium)
        )
    }
}