package com.example.android_data.repository

import com.example.android_data.FirestoreTopicDataSource
import com.example.android_data.Topic
import com.example.android_data.TopicDataSource
import com.example.android_data.getTopicList
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
    override fun getTopic(): Flow<List<Topic>> {
        return flow {
            emit(getTopicList())
        }
    }
}