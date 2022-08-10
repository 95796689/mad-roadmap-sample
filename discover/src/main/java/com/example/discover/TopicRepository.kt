package com.example.discover

import com.example.android_data.TopicDao
import com.example.base_android.data
import javax.inject.Inject

class TopicRepository @Inject constructor(
    private val topicDataSource: TopicDataSource,
    private val topicDao: TopicDao
) {
    fun getTopics() {

    }
}