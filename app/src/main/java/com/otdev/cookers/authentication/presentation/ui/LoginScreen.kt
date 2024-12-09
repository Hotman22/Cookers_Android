package com.otdev.cookers.authentication.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.otdev.cookers.authentication.presentation.viewmodel.LogInViewModel
import com.otdev.cookers.ui.theme.CookersTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewmodel: LogInViewModel = hiltViewModel()
) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()
    val onLogIn = { fullName: String, password: String ->
        viewmodel.sendIntent(
            LogInViewModel.UIAction.LogIn(fullName, password)
        )
    }

    LoginScreenContent(uiState, onLogIn, modifier = modifier)
}

@Composable
fun LoginScreenContent(
    uiState: LogInViewModel.UIState,
    onLogIn: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val onFullNameUpdated = remember { { updatedFullName: String -> fullName = updatedFullName } }
    val onPasswordUpdated = remember { { updatedPassWord: String -> password = updatedPassWord } }

    Column(modifier) {
        UserNameTextField(fullName, onFullNameUpdated)
        PasswordTextField(password, onPasswordUpdated)

        Button(
            onClick = { onLogIn(fullName, password) },
            modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp)
                .width(150.dp)
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator(color = Color.White, modifier = Modifier.height(25.dp))
                else -> Text("Se connecter")
            }
        }
    }

}

@Composable
fun UserNameTextField(
    fullName: String,
    onFullNameUpdated: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        placeholder = { Text("Nom utilisateur") },
        value = fullName,
        onValueChange = onFullNameUpdated,
        singleLine = true,
        modifier = modifier,
    )
}


@Composable
fun PasswordTextField(
    password: String, onPasswordUpdated: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        placeholder = { Text("Mot de passe") },
        value = password,
        onValueChange = onPasswordUpdated,
        singleLine = true,
        modifier = modifier.padding(top = 20.dp),
    )
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(
    @PreviewParameter(LoginParameterProvider::class) uiState: LogInViewModel.UIState
) {
    CookersTheme {
        LoginScreenContent(uiState, onLogIn = { _, _ -> })
    }
}

class LoginParameterProvider : PreviewParameterProvider<LogInViewModel.UIState> {
    override val values = sequenceOf(
        LogInViewModel.UIState(isLoading = true),
        LogInViewModel.UIState(isLoading = false)
    )
}