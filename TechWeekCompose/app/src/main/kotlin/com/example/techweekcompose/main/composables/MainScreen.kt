package com.example.techweekcompose.main.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import com.example.techweekcompose.R
import com.example.techweekcompose.common.composables.CustomDivider
import com.example.techweekcompose.common.composables.InfiniteListHandler
import com.example.techweekcompose.main.MainViewModel
import com.example.techweekcompose.main.UIState
import com.example.techweekcompose.models.PokemonDomain
import com.example.techweekcompose.theme.PokemonTheme
import org.koin.androidx.compose.viewModel

@Composable
fun MainScreen() {
    val mainViewModel: MainViewModel by viewModel()
    val uiState by mainViewModel.uiState
    when (uiState) {
        UIState.GenericError -> {}
        UIState.Loading -> {}
        is UIState.Result -> {
            PokemonListComponent((uiState as UIState.Result))
        }
    }
}

@Composable
fun PokemonListComponent(result: UIState.Result) {
    val mainViewModel: MainViewModel by viewModel()
    val scrollState = rememberLazyListState()
    val nextPage = rememberUpdatedState(newValue = result.nextPage)
    LazyColumn(
        state = scrollState,
        modifier = Modifier.background(color = PokemonTheme.colors.primary)
    ) {
        itemsIndexed(result.pokemonList) { index, pokemon ->
            PokemonListItem(
                pokemon = pokemon,
                modifier = Modifier.padding(
                    start = PokemonTheme.dimensions.medium,
                    top = PokemonTheme.dimensions.medium,
                    bottom = PokemonTheme.dimensions.medium
                )
            )
            if (index < result.pokemonList.lastIndex) CustomDivider()
        }
    }

    InfiniteListHandler(listState = scrollState, buffer = 2) {
        mainViewModel.fetchNextPage(nextPage.value)
    }
}

@Composable
fun PokemonListItem(pokemon: PokemonDomain, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        val painter = rememberImagePainter(pokemon.urlImage)
        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.main_icon_description, pokemon.name),
            modifier = Modifier
                .size(PokemonTheme.iconDimensions.regular)
                .clip(CircleShape),
            contentScale = ContentScale.Fit,
        )
        Text(
            text = pokemon.name,
            style = PokemonTheme.typography.h6,
            color = PokemonTheme.colors.secondary,
            modifier = Modifier
                .padding(
                    horizontal = PokemonTheme.dimensions.medium,
                    vertical = PokemonTheme.dimensions.small
                )
                .align(Alignment.CenterVertically)
        )
    }
}

