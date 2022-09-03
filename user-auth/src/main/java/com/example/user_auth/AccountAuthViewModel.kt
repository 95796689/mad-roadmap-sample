package com.example.user_auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class AccountAuthViewModel @Inject constructor(
    private val accountAuthRepository: AccountAuthRepository
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    var errorMessage = mutableStateOf("")
        private set

    var loginSuccess = mutableStateOf(false)
        private set

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue)
    }

    fun isLoggedIn(): Boolean {
        return accountAuthRepository.isLoggedIn()
    }

    fun authenticate() {
        viewModelScope.launch {
            val authResult = accountAuthRepository.authenticate(uiState.value.email, uiState.value.password)
            parseAuthResult(authResult)
        }
    }

    fun createAccount() {
        viewModelScope.launch {
            val authResult = accountAuthRepository.createAccount(
                uiState.value.email,
                uiState.value.password,
                uiState.value.name
            )
            parseAuthResult(authResult)
        }
    }

    private fun parseAuthResult(authResult: AuthState) {
        if (authResult is AuthStateSuccess) {
            errorMessage.value = ""
            loginSuccess.value = true
        } else if (authResult is AuthStateFailure) {
            authResult.throwable?.message?.let {
                errorMessage.value = it
                loginSuccess.value = false
            }
        }
    }
}