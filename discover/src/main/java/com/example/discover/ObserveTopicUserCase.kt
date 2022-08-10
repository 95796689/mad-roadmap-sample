package com.example.discover

import com.example.android_data.Topic
import com.example.android_data.TopicDao
import com.example.base_android.SubjectInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTopicUserCase @Inject constructor(
    private val topicDao: TopicDao
) : SubjectInteractor<ObserveTopicUserCase.Params, List<Topic>>() {

    data class Params(val count: Int = 20)

    override fun createObservable(params: Params): Flow<List<Topic>> {
        return topicDao.entriesObservable(params.count)
    }
}