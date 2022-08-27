package com.example.user_auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseAccountAuthDataSource @Inject constructor() {

    @Inject
    lateinit var auth: FirebaseAuth

//    suspend fun createAnonymousAccount(): AuthResult {
//        return suspendCoroutine { continuation ->
//            auth.signInAnonymously()
//                .addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        Timber.d("signInAnonymously:success")
//                        continuation.resume(it.result)
//                    } else {
//                        Timber.w("signInAnonymously:failure", it.exception)
//                        continuation.resumeWithException(it.exception!!)
//                    }
//                }
//        }
//    }

    suspend fun authenticate(email: String, password: String): AuthResult {
        return suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Timber.d("signInWithEmail:success")
                        continuation.resume(it.result)
                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.w("signInWithEmail:failure", it.exception)
                        continuation.resumeWithException(it.exception!!)
                    }
                }
        }
    }

    suspend fun createAccount(email: String, password: String): AuthResult {
        return suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Timber.d("createUserWithEmail:success")
                        continuation.resume(it.result)
                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.w("createUserWithEmail:failure", it.exception)
                        continuation.resumeWithException(it.exception!!)
                    }
                }
        }
    }

//    fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
//        val credential = EmailAuthProvider.getCredential(email, password)
//
//        auth.currentUser?.linkWithCredential(credential)
//            ?.addOnCompleteListener {
//                if (it.isSuccessful) {
//                    Timber.d("linkAccount:success")
//                } else {
//                    Timber.d("linkAccount:failure", it.exception)
//                }
//            }
//    }
}