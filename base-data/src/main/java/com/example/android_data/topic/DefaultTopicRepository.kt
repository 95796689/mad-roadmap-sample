package com.example.android_data

import androidx.room.withTransaction
import com.example.android_data.topic.Topic
import com.example.android_data.topic.TopicDao
import com.example.android_data.user.UserDao
import com.example.base_android.AppCoroutineDispatchers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

interface TopicRepository {
    fun getTopic(): Flow<List<TopicWithUser>>
    suspend fun addTopic(title: String, content: String): AddTopicStatus
    fun observeTopic()
}

class DefaultTopicRepository @Inject constructor(
    private val topicDataSource: TopicDataSource,
    private val database: SadDatabase,
    private val topicDao: TopicDao,
    private val userDao: UserDao,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
): TopicRepository {

    init {
        observeTopic()
    }

    override suspend fun addTopic(title: String, content: String): AddTopicStatus {
        return withContext(appCoroutineDispatchers.io) {
            topicDataSource.addTopic(title, content)
        }
    }

    override fun getTopic(): Flow<List<TopicWithUser>> {
        return topicDao.observeTopicWithUser()
    }

    override fun observeTopic() {
        val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        scope.launch {
            topicDataSource.getTopic().collect {
                database.withTransaction {
                    it.topic?.let { topic ->
                        topicDao.insertTopic(topic)
                    }
                    it.user?.let { user ->
                        userDao.insertUser(user)
                    }
                }
            }
        }
    }
}