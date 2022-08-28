package com.example.user_auth

import com.example.base_android.AppCoroutineDispatchers
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountAuthRepository @Inject constructor(
    private val firebaseAccountAuthDataSource: FirebaseAccountAuthDataSource,
    private val appDispatcher: AppCoroutineDispatchers
) {
    suspend fun authenticate(email: String, password: String) {
        return withContext(appDispatcher.io) {
            firebaseAccountAuthDataSource.authenticate(email, password)
        }
    }

    suspend fun createAccount(email: String, password: String) {
        return withContext(appDispatcher.io) {
            firebaseAccountAuthDataSource.createAccount(email, password)
        }
    }

    fun isLoggedIn(): Boolean {
        val user = Firebase.auth.currentUser
        return user == null
    }
}