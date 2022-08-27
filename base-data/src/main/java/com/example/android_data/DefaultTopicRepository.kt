package com.example.android_data

import com.example.base_android.AppCoroutineDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface TopicRepository {
    fun getTopic(): Flow<List<Topic>>
}

class DefaultTopicRepository @Inject constructor(
    private val topicDataSource: TopicDataSource,
    private val topicDao: TopicDao,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
): TopicRepository {
    suspend fun refresh() {
        withContext(appCoroutineDispatchers.io) {
            topicDataSource.getTopic()
                .collect {
                    topicDao.insertAll(it)
                }
        }
    }

    override fun getTopic(): Flow<List<Topic>> {
        return topicDao.entriesObservable()
    }
}