package com.example.android_data

import com.example.base_android.AppCoroutineDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface TopicRepository {
    fun getTopic(): Flow<List<Topic>>
    suspend fun addTopic(title: String, content: String)
}

class DefaultTopicRepository @Inject constructor(
    private val topicDataSource: TopicDataSource,
    private val topicDao: TopicDao,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
): TopicRepository {
    override suspend fun addTopic(title: String, content: String) {
        withContext(appCoroutineDispatchers.io) {
            topicDataSource.addTopic(title, content)
        }
    }

    override fun getTopic(): Flow<List<Topic>> {
        return topicDao.entriesObservable()
    }
}