package com.example.catdogincompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.catdogincompose.R
import com.example.catdogincompose.ui.theme.CatDogInComposeTheme
import com.example.catdogincompose.ui.viewmodel.PhraseViewModel

/**
 * Composable for the Phrase screen.
 *
 * This composable displays a greeting and a phrase to the user.
 */
@Composable
fun PhraseScreen(
    modifier: Modifier,
    userName: String,
    viewModel: PhraseViewModel = hiltViewModel()
){

    val currentPhrase by viewModel.currentPhrase.collectAsState()
    val isCatSelected by viewModel.isCatSelected.collectAsState()
    val isDogSelected by viewModel.isDogSelected.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "Cat",
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clickable { viewModel.loadRandomCatPhrase() },
                colorFilter = if(isCatSelected) ColorFilter.tint(MaterialTheme.colorScheme.tertiary) else null
            )
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "Dog",
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clickable { viewModel.loadRandomDogPhrase() },
                colorFilter = if(isDogSelected) ColorFilter.tint(MaterialTheme.colorScheme.tertiary) else null
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.greeting, userName),
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground)
        )

        Text(
            text = currentPhrase,
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.loadNewPhrase() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary //substituido
            ),
        ) {
            Text(
                stringResource(id = R.string.button_new_phrase),
                color = MaterialTheme.colorScheme.background //substituido
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPhraseScreen() {
    CatDogInComposeTheme {
        PhraseScreen(modifier = Modifier.fillMaxSize(), userName = "User")
    }
}

