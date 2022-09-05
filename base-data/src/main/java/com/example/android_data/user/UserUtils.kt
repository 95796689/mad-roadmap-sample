package com.example.android_data.user

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object UserUtils {
    fun getMeAsUser(): User? {
        val user = Firebase.auth.currentUser
        user?.let {
            return User(userId = it.uid, name = it.displayName)
        }
        return null
    }

    fun getMeId(): String {
        val user = Firebase.auth.currentUser
        user?.let {
            return it.uid
        }
        return ""
    }
}