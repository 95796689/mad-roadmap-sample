package com.example.user_auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onSignInClick() {
        accountService.authenticate(uiState.value.email, uiState.value.password) { error ->
            if (error == null) {
                linkWithEmail()
            } else {
                Timber.d("authenticate error")
            }
        }
    }

    private fun linkWithEmail() {
        accountService.linkAccount(uiState.value.email, uiState.value.password) {
            Timber.d("link account error", it?.message)
        }
    }
}