package com.example.sad.initializer

import android.content.Context
import androidx.startup.Initializer
import com.example.sad.BuildConfig
import com.example.sad.util.CrashlyticsTree
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class TimberInitializer : Initializer<Unit> {

    @Inject
    lateinit var crashlytics: FirebaseCrashlytics

    override fun create(context: Context) {
        //injecting dependencies from Hilt
        InitializerEntryPoint.resolve(context).inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashlyticsTree(crashlytics))
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        //requires Dependency Graph to be initialized
        return listOf(DependencyGraphInitializer::class.java)
    }
}