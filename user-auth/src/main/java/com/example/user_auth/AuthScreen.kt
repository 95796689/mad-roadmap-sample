package com.example.user_auth

import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.base_ui_compose.Layout

@Composable
fun AuthScreen() {
    AuthScreen(viewModel = hiltViewModel())
}

@Composable
internal fun AuthScreen(
    viewModel: AccountAuthViewModel
) {
    val uiState by viewModel.uiState

    Column(
        modifier = Modifier.padding(
            horizontal = Layout.bodyMargin,
            vertical = Layout.gutter,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(80.dp))
        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fillMaxWidth())
        Spacer(Modifier.height(20.dp))
        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fillMaxWidth())
        Spacer(Modifier.height(80.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(60.dp)
        ) {
            LoginConfirmButton(viewModel::authenticate, Modifier.fillMaxWidth())
            SignupConfirmButton(viewModel::createAccount, Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun EmailField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(R.string.email_input)) },
    )
}

@Composable
fun PasswordField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(R.string.password_input)) },
    )
}

@Composable
fun LoginConfirmButton(authenticateAction: () -> Unit,
                       modifier: Modifier = Modifier) {
    Button(
        onClick = authenticateAction,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.login_text))
    }
}

@Composable
fun SignupConfirmButton(createAccountAction: () -> Unit,
                       modifier: Modifier = Modifier) {
    Button(
        onClick = createAccountAction,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.signup_text))
    }
}