package com.example.android_data.repository

import com.example.android_data.*
import com.example.android_data.getTopicList
import com.example.android_data.util.DatabaseTest
import com.example.base_android.inject.BaseProvidesModule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@UninstallModules(RoomDatabaseModule::class, BaseProvidesModule::class, TopicDataSourceModule::class)
@HiltAndroidTest
class TopicRepositoryTest : DatabaseTest() {

    @Inject lateinit var topicRepository: TopicRepository
    @Inject lateinit var topicDao: TopicDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testGetTopic() = runTest {
        val topics = getTopicList()
        topicDao.insertAll(topics)
        val topic = topicRepository.getTopic().first()[0]
        assertThat(topics[0], `is`(topic))
    }
}