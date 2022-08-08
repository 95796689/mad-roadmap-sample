package com.example.base_android.util

interface Analytics {
    fun trackScreenView(
        label: String,
        route: String?,
        arguments: Any?,
    )
}