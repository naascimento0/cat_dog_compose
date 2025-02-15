package com.example.catdogincompose.screen

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catdogincompose.R
import com.example.catdogincompose.ui.theme.CatDogInComposeTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSaveClick: (String) -> Unit
) {
    var name by remember { mutableStateOf(value = "") }
    val alataFont = FontFamily(Font(R.font.alata_regular))

    Column(modifier = modifier
        .background(color = colorResource(id = R.color.blue)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = alataFont,
                color = colorResource(id = R.color.white),
            ),
            modifier = Modifier.paddingFromBaseline(top = 50.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text(
                    text = stringResource(id = R.string.hint),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = alataFont,
                        color = colorResource(id = R.color.white),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onSaveClick(name) },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.light_blue)
            ),
        ) {
            Text(stringResource(id = R.string.button_save), color = colorResource(id = R.color.white))
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
