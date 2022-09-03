package com.example.android_data.topic

import com.example.android_data.DefaultTopicRepository
import com.example.android_data.FirestoreTopicDataSource
import com.example.android_data.TopicDataSource
import com.example.android_data.TopicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TopicModule {
    @Binds
    @Singleton
    internal abstract fun provideTopicDataSource(dataSource: FirestoreTopicDataSource): TopicDataSource

    @Binds
    @Singleton
    internal abstract fun provideTopicRepository(repository: DefaultTopicRepository): TopicRepository
}