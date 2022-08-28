package com.example.user_auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class AccountAuthViewModel @Inject constructor(
    private val accountAuthRepository: AccountAuthRepository
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun isLoggedIn(): Boolean {
        return accountAuthRepository.isLoggedIn()
    }

    fun authenticate() {
        viewModelScope.launch {
            accountAuthRepository.authenticate(uiState.value.email, uiState.value.password)
        }
    }

    fun createAccount() {
        viewModelScope.launch {
            accountAuthRepository.createAccount(uiState.value.email, uiState.value.password)
        }
    }
}