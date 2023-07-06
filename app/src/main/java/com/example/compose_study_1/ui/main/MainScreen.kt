package com.example.compose_study_1.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_study_1.ui.theme.Composestudy1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    appbarName: String,
    scaffoldContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(name = appbarName)
        },
        content = scaffoldContent
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Composestudy1Theme {
        MainScreen(appbarName = "Main AppBar") { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Greeting(name = "Hello World!!")
                Greeting(name = "Test 1")
                Greeting(name = "Test 2")
                Greeting(name = "Test test test test test test 3")
            }
        }
    }
}