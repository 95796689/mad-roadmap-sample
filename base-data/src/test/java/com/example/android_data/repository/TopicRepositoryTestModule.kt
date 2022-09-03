package com.example.android_data.repository

import com.example.android_data.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TopicRepositoryTestModule {
    @Binds
    @Singleton
    internal abstract fun provideTopicDataSource(dataSource: TestTopicDataSource): TopicDataSource
}

class TestTopicDataSource @Inject constructor(): TopicDataSource {
    override fun getTopic(): Flow<TopicWithUser> {
        return flow {
            emit(getTopicWithUser())
        }
    }

    override suspend fun addTopic(title: String, content: String): AddTopicStatus {
        return AddTopicSuccess
    }
}