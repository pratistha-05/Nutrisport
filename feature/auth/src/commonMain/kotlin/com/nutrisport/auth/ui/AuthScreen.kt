package com.nutrisport.feature.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nutrisport.utils.TextPrimary
import com.example.nutrisport.utils.TextSecondary
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.nutrisport.auth.ui.AuthViewModel
import com.nutrisport.feature.auth.component.GoogleButton
import com.nutrisport.shared.FontSize
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthScreen(
    navigateToHome: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val viewModel = koinViewModel<AuthViewModel>()
    var loadingState by remember { mutableStateOf(false) }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 24.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "NUTRISPORT",
                    textAlign = TextAlign.Center,
                    fontSize = FontSize.EXTRA_LARGE,
                    color = TextSecondary
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Sign in to continue",
                    textAlign = TextAlign.Center,
                    fontSize = FontSize.EXTRA_REGULAR,
                    color = TextPrimary
                )
            }
            GoogleButtonUiContainerFirebase(
                linkAccount = false,
                onResult = { result ->
                    result.onSuccess { user ->
                        viewModel.createCustomer(
                            user = user,
                            onSuccess = {
                                scope.launch {
                                    navigateToHome()
                                }
                            },
                            onError = {
                            }
                        )
                        loadingState = false
                    }.onFailure { error ->
                        if (error.message?.contains("A network error") == true) {
                        } else if (error.message?.contains("Idtoken is null") == true) {
                        } else {
                        }
                        loadingState = false
                    }
                }
            ) {
                GoogleButton(
                    loading = loadingState,
                    onClick = {
                        loadingState = true
                        this@GoogleButtonUiContainerFirebase.onClick()
                    }
                )
            }
        }
    }
}