package com.example.user_auth

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import javax.inject.Inject

class AccountServiceImp @Inject constructor() : AccountService {

    @Inject
    lateinit var auth: FirebaseAuth

    override fun createAnonymousAccount(onResult: (Throwable?) -> Unit) {
        auth.signInAnonymously()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Timber.d("signInAnonymously:success")
                } else {
                    Timber.w("signInAnonymously:failure", it.exception)
                }
            }
    }

    override fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d("signInWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.w("signInWithEmail:failure", it.exception)
                }
            }
    }

    override fun createAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d("createUserWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.w("createUserWithEmail:failure", it.exception)
                }
            }
    }

    override fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        val credential = EmailAuthProvider.getCredential(email, password)

        auth.currentUser?.linkWithCredential(credential)
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Timber.d("linkAccount:success")
                } else {
                    Timber.d("linkAccount:failure", it.exception)
                }
            }
    }
}