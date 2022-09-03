package com.example.user_auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseAccountAuthDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun authenticate(email: String, password: String): AuthState {
        return suspendCancellableCoroutine { continuation ->
            if (email.isEmpty() || password.isEmpty()) {
                continuation.resume(AuthStateFailure(Throwable("empty passport or email")))
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Timber.d("signInWithEmail:success")
                            continuation.resume(AuthStateSuccess)
                        } else {
                            // If sign in fails, display a message to the user.
                            Timber.w("signInWithEmail:failure", it.exception)
                            continuation.resume(AuthStateFailure(it.exception))
                        }
                    }
            }
        }
    }

    suspend fun createAccount(email: String, password: String, name: String): AuthState {
        return suspendCancellableCoroutine { continuation ->
            if (email.isEmpty() || password.isEmpty()) {
                continuation.resume(AuthStateFailure(Throwable("empty passport or email")))
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Timber.d("createUserWithEmail:success")
                            updateName(name)
                            continuation.resume(AuthStateSuccess)
                        } else {
                            // If sign in fails, display a message to the user.
                            Timber.w("createUserWithEmail:failure", it.exception)
                            continuation.resume(AuthStateFailure(it.exception))
                        }
                    }
            }
        }
    }

    private fun updateName(name: String) {
        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }

        auth.currentUser!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("User profile updated.")
                }
            }
    }
}