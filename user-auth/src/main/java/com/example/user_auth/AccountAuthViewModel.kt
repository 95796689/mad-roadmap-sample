package com.example.user_auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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

    suspend fun onSignInClick() {
        val authResult = accountAuthRepository.authenticate(uiState.value.email, uiState.value.password)
    }

    suspend fun createAccount() {
        val authResult = accountAuthRepository.createAccount(uiState.value.email, uiState.value.password)
    }
}