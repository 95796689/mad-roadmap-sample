package com.example.base_android.util

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import javax.inject.Inject
import javax.inject.Provider

internal class SadAnalytics @Inject constructor(
    private val firebaseAnalytics: Provider<FirebaseAnalytics>,
) : Analytics {
    override fun trackScreenView(
        label: String,
        route: String?,
        arguments: Any?,
    ) {
        try {
            firebaseAnalytics.get().logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, label)
                if (route != null) param("screen_route", route)

                // Expand out the rest of the parameters
                when {
                    arguments is Bundle -> {
                        for (key in arguments.keySet()) {
                            val value = arguments.get(key).toString()
                            // We don't want to include the label or route twice
                            if (value == label || value == route) continue

                            param("screen_arg_$key", value)
                        }
                    }
                    arguments != null -> param("screen_arg", arguments.toString())
                }
            }
        } catch (t: Throwable) {
            // Ignore, Firebase might not be setup for this project
        }
    }
}