package com.example.catdogincompose.screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catdogincompose.R
import com.example.catdogincompose.ui.theme.CatDogInComposeTheme

@Composable
fun PhraseScreen(modifier: Modifier, userName: String) {

    val catPhrases = listOf(
        "Os gatos têm um órgão extra que lhes permite saborear cheiros.",
        "Um gato pode fazer mais de 100 sons diferentes.",
        "Os gatos têm 32 músculos em cada orelha, permitindo-lhes girar suas orelhas para ouvir melhor.",
        "Os bigodes dos gatos são altamente sensíveis e podem detectar mudanças mínimas no ambiente."
    )

    val dogPhrases = listOf(
        "Os cães têm um olfato que é até 100.000 vezes mais sensível que o dos humanos.",
        "Os cães conseguem entender cerca de 165 palavras e sinais diferentes.",
        "Os cães possuem glândulas sudoríparas apenas nas patas, não pelo corpo.",
        "Raças como o Basenji não latem, mas emitem um som peculiar conhecido como \"yodel\"."
    )

    var currentAnimalList by remember { mutableStateOf(catPhrases) }
    var currentPhrase by remember { mutableStateOf(currentAnimalList.random()) }

    var isCatSelected by remember { mutableStateOf(value = true) }
    var isDogSelected by remember { mutableStateOf(value = false) }

    val alataFont = FontFamily(Font(R.font.alata_regular))

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), // substituido
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary), // substituido
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "Cat",
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clickable {
                        currentAnimalList = catPhrases
                        currentPhrase = currentAnimalList.random()
                        isCatSelected = true
                        isDogSelected = false
                    },
                colorFilter = if(isCatSelected) ColorFilter.tint(MaterialTheme.colorScheme.tertiary) else null // substituido
            )
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "Dog",
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clickable {
                        currentAnimalList = dogPhrases
                        currentPhrase = currentAnimalList.random()
                        isCatSelected = false
                        isDogSelected = true
                    },
                colorFilter = if(isDogSelected) ColorFilter.tint(MaterialTheme.colorScheme.tertiary) else null // substituido
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.greeting, userName),
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground) //substituido
        )

        Text(
            text = currentPhrase,
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground), //substituido
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                currentPhrase = currentAnimalList.random()
            },
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

