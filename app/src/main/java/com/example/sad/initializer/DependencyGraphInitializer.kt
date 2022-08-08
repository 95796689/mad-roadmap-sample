package com.example.sad.initializer

import android.content.Context
import androidx.startup.Initializer

open class DependencyGraphInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        //this will lazily initialize ApplicationComponent before Application's `onCreate`
        InitializerEntryPoint.resolve(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}