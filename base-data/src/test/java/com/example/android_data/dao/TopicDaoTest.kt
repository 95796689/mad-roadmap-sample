package com.example.android_data.dao

import com.example.android_data.*
import com.example.android_data.insertTopic
import com.example.android_data.topic.TopicDao
import com.example.android_data.util.DataTestRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@UninstallModules(RoomDatabaseModule::class)
@HiltAndroidTest
class TopicDaoTest : DataTestRule() {
    @Inject lateinit var database: SadDatabase
    @Inject lateinit var topicDao: TopicDao

    @Before
    fun setUp() {
        hiltRule.inject()

        runBlocking {
            insertTopic(database)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insert() = runTest {
        val topic = getTopic()
        topicDao.insertTopic(topic)
        val id = topic.id
        val topicResult = topicDao.queryTopicWithId(id)
        assertThat(topicResult, `is`(topic))
    }

    @Test
    fun delete() = runTest {
        val topic = getTopic()
        topicDao.insertTopic(topic)
        topicDao.deleteTopic(topic)
        assertThat(topicDao.queryTopicWithId(topic.id), `is`(nullValue()))
    }
}