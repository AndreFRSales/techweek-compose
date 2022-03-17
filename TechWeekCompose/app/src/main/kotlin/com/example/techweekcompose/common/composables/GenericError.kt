package com.example.techweekcompose.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.techweekcompose.R
import com.example.techweekcompose.theme.PokemonTheme

@Composable
fun GenericError(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.padding(PokemonTheme.dimensions.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = PokemonTheme.colors.secondary,
            style = PokemonTheme.typography.h1,
            textAlign = TextAlign.Center
        )
        Text(
            text = description,
            color = PokemonTheme.colors.primaryDark,
            style = PokemonTheme.typography.subtitle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = PokemonTheme.dimensions.medium)
        )
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = PokemonTheme.dimensions.large)
        ) {
            Text(
                text = stringResource(id = R.string.main_generic_error_button),
                textAlign = TextAlign.Center, style = PokemonTheme.typography.button
            )
        }
    }
}


@Preview
@Composable
fun GenericErrorPreview() {
    MaterialTheme {
        GenericError(title = "", description = "")
    }
}
