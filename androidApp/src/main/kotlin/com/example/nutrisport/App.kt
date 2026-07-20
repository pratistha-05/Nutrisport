package com.example.nutrisport

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutrisport.Screen
import com.example.navigation.SetupNavGraph
import com.nutrisport.data.domain.CustomerRepository
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        val customerRespoitory = koinInject<CustomerRepository>()

        val isUserAuthenticated = remember {customerRespoitory.getCurrentUserId()!=null}
        val startDestination = remember {
            if(isUserAuthenticated)
                Screen.HomeGraph

            else
                Screen.Auth

        }

        SetupNavGraph (
            startDestination=startDestination
        )
    }
}