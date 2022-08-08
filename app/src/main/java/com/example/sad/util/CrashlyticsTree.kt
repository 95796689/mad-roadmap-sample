package com.example.sad.util

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree(
    private val firebaseCrashlytics: FirebaseCrashlytics
) : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        firebaseCrashlytics.log(message)
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.INFO
    }
}