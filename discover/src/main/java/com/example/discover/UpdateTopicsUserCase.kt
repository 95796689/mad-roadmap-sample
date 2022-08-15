package com.example.discover

import com.example.base_android.AppCoroutineDispatchers
import com.example.base_android.Interactor
import javax.inject.Inject

class UpdateTopicsUserCase @Inject constructor(
    private val topicRepository: TopicRepository,
    private val dispatchers: AppCoroutineDispatchers,
) : Interactor<Int>() {
    override suspend fun doWork(params: Int) {
        topicRepository.refresh()
    }
}