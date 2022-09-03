package com.example.discover

import com.example.android_data.topic.Topic
import com.example.android_data.TopicRepository
import com.example.android_data.TopicWithUser
import com.example.base_android.SubjectInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTopicUserCase @Inject constructor(
    private val repository: TopicRepository
) : SubjectInteractor<Int, List<TopicWithUser>>() {
    override fun createObservable(params: Int): Flow<List<TopicWithUser>> {
        return repository.getTopic()
    }
}