package com.example.techweekcompose.main.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.techweekcompose.R
import com.example.techweekcompose.common.composables.CustomDivider
import com.example.techweekcompose.common.composables.CustomLoading
import com.example.techweekcompose.common.composables.GenericError
import com.example.techweekcompose.common.composables.InfiniteListHandler
import com.example.techweekcompose.main.MainViewModel
import com.example.techweekcompose.main.PaginationErrorState
import com.example.techweekcompose.main.UIState
import com.example.techweekcompose.models.PokemonDomain
import com.example.techweekcompose.navigation.DestinationsFactory
import com.example.techweekcompose.theme.PokemonTheme
import org.koin.androidx.compose.viewModel

@Composable
fun MainScreen(navController: NavController) {
    val mainViewModel: MainViewModel by viewModel()
    val uiState by mainViewModel.uiState
    val paginationErrorState by mainViewModel.paginationErrorState
    when (val result: UIState = uiState) {
        UIState.GenericError -> MainGenericError(onClick = { mainViewModel.retry() })
        UIState.Loading -> MainLoading()
        is UIState.Result -> {
            PokemonListComponent(navController, result) { mainViewModel.fetchNextPage(result.nextPage) }
        }
    }

    when (paginationErrorState) {
        PaginationErrorState.Error -> MainSnackBar()
        PaginationErrorState.Initial -> {}
    }
}

@Composable
fun PokemonListComponent(
    navController: NavController,
    result: UIState.Result,
    fetchNextPage: (String?) -> Unit
) {
    val scrollState = rememberLazyListState()
    val nextPage by rememberUpdatedState(newValue = result.nextPage)
    val weight = if (result.shouldShowPaginationLoading) 1f else 0.1f

    Column(Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .weight(weight)
                .background(color = PokemonTheme.colors.primary),
        ) {
            itemsIndexed(result.pokemonList) { index, pokemon ->
                PokemonListItem(
                    pokemon = pokemon,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true),
                            onClick = {
                                pokemon.run {
                                    navController.navigate(
                                        DestinationsFactory.createDetailScreenDestination(
                                            url,
                                            urlImage,
                                            name
                                        )
                                    )
                                }
                            })
                        .padding(
                            start = PokemonTheme.dimensions.medium,
                            top = PokemonTheme.dimensions.medium,
                            bottom = PokemonTheme.dimensions.medium
                        )
                )
                if (index < result.pokemonList.lastIndex) CustomDivider()
            }
        }

        if (result.shouldShowPaginationLoading) {
            PagingLoading()
        }

        InfiniteListHandler(listState = scrollState, buffer = 2) {
            fetchNextPage(nextPage)
        }
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

@Composable
fun MainGenericError(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    GenericError(
        modifier = modifier.fillMaxSize(),
        title = stringResource(id = R.string.main_generic_error_title),
        description = stringResource(id = R.string.main_generic_error_description),
        onClick = onClick
    )
}

@Composable
fun MainLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.main_loading_title),
            textAlign = TextAlign.Center,
            style = PokemonTheme.typography.caption,
            color = PokemonTheme.colors.secondary
        )
        CustomLoading(modifier = Modifier.padding(top = PokemonTheme.dimensions.medium))
    }
}

@Composable
fun PagingLoading() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PokemonTheme.colors.primary),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        CustomLoading(modifier = Modifier.size(PokemonTheme.iconDimensions.small))
    }
}

@Composable
fun MainSnackBar() {
    val paginationError = stringResource(id = R.string.main_pagination_error)
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = Unit) {
        snackBarHostState.showSnackbar(paginationError)
    }

    Box(modifier = Modifier.fillMaxHeight()) {
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}