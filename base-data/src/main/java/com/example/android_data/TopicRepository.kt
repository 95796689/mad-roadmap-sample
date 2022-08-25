package com.example.android_data

import com.example.android_data.Topic
import com.example.android_data.TopicDao
import com.example.base_android.AppCoroutineDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopicRepository @Inject constructor(
    private val topicDataSource: TopicDataSource,
    private val topicDao: TopicDao,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
) {
    suspend fun refresh() {
        withContext(appCoroutineDispatchers.io) {
            topicDataSource.getTopic()
                .collect {
                    topicDao.insertAll(it)
                }
        }
    }

    fun getTopic(): Flow<List<Topic>> {
        return topicDao.entriesObservable()
    }
}