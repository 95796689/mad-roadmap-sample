package com.example.discover

import com.dropbox.android.external.store4.Fetcher
import com.dropbox.android.external.store4.SourceOfTruth
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreBuilder
import com.example.android_data.Topic
import com.example.android_data.TopicDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Singleton

//typealias TopicRepository = Store<Int, List<Topic>>

@Module
@InstallIn(SingletonComponent::class)
internal object TopicModule {
//    @Provides
//    @Singleton
//    fun provideTopicStore(
//        topicDataSource: FirestoreTopicDataSource,
//        topicDao: TopicDao
//    ): TopicRepository = StoreBuilder.from(
//        fetcher = Fetcher.ofFlow {
//            topicDataSource.getTopic()
//        },
//        sourceOfTruth = SourceOfTruth.of(
//            reader = { page ->
//                topicDao.entriesObservable()
//            },
//            writer = { _, response ->
//                topicDao.insertAll(response)
//            },
//            delete = topicDao::deletePage,
//            deleteAll = topicDao::deleteAll,
//        )
//    ).build()
}