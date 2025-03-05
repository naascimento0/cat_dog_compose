package com.example.catdogincompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.catdogincompose.R
import com.example.catdogincompose.ui.theme.CatDogInComposeTheme
import com.example.catdogincompose.ui.viewmodel.MainViewModel

/**
 * Composable for the Main screen.
 *
 * This composable displays a title, a text field, and a button to save the user's name.
 */
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSaveClick: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    var inputName by remember { mutableStateOf("") }

    Column(modifier = modifier
        .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title),
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier.paddingFromBaseline(top = 50.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = inputName,
            onValueChange = { inputName = it },
            label = {
                Text(
                    text = stringResource(id = R.string.hint),
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
                    textAlign = TextAlign.Center
                )
            },
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.saveName(inputName)
                onSaveClick(inputName)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            enabled = inputName.isNotEmpty()
        ) {
            Text(stringResource(id = R.string.button_save), color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    CatDogInComposeTheme {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            onSaveClick = {}
        )
    }
}