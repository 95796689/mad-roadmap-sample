package com.example.discover

import com.example.android_data.Topic
import com.example.base_android.UiMessage

internal class DiscoverViewState(
    val topicItems: List<Topic> = emptyList(),
    val topicRefreshing: Boolean = false,
    val message: UiMessage? = null
) {
    companion object {
        val Empty = DiscoverViewState()
    }
}