package com.example.discover

import com.example.android_data.Topic
import com.example.base_android.SubjectInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTopicUserCase @Inject constructor(
    private val repository: TopicRepository
) : SubjectInteractor<Int, List<Topic>>() {
    override fun createObservable(params: Int): Flow<List<Topic>> {
        return repository.getTopic()
    }
}