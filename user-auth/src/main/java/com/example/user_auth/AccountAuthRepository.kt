package com.example.user_auth

import com.example.base_android.AppCoroutineDispatchers
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountAuthRepository @Inject constructor(
    private val firebaseAccountAuthDataSource: FirebaseAccountAuthDataSource,
    private val appDispatcher: AppCoroutineDispatchers
) {
    suspend fun authenticate(email: String, password: String): AuthResult {
        return withContext(appDispatcher.io) {
            firebaseAccountAuthDataSource.authenticate(email, password)
        }
    }

    suspend fun createAccount(email: String, password: String): AuthResult {
        return withContext(appDispatcher.io) {
            firebaseAccountAuthDataSource.createAccount(email, password)
        }
    }
}