package com.example.android_data

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