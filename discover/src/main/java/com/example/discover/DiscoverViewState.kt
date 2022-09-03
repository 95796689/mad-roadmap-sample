package com.example.discover

import com.example.android_data.TopicWithUser
import com.example.android_data.topic.Topic
import com.example.base_android.UiMessage

internal class DiscoverViewState(
    val topicItems: List<TopicWithUser> = emptyList(),
    val topicRefreshing: Boolean = false,
    val message: UiMessage? = null
) {
    companion object {
        val Empty = DiscoverViewState()
    }
}