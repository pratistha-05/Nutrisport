package com.example.nutrisport

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigation.SetupNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        SetupNavGraph ()
    }
}