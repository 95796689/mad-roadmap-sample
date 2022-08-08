package com.example.sad.initializer

import android.content.Context
import androidx.startup.Initializer
import coil.Coil
import coil.ImageLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import javax.inject.Inject

class CoilInitializer : Initializer<Unit> {

    @Inject
    lateinit var okHttpClient : OkHttpClient

    override fun create(@ApplicationContext context: Context) {
        //injecting dependencies from Hilt
        InitializerEntryPoint.resolve(context).inject(this)

        Coil.setImageLoader {
            ImageLoader.Builder(context)
                .okHttpClient(okHttpClient)
                .build()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(DependencyGraphInitializer::class.java)
    }
}