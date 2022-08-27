package com.example.discover

import com.example.android_data.DefaultTopicRepository
import com.example.base_android.AppCoroutineDispatchers
import com.example.base_android.Interactor
import javax.inject.Inject

class UpdateTopicsUserCase @Inject constructor(
    private val topicRepository: DefaultTopicRepository,
    private val dispatchers: AppCoroutineDispatchers,
) : Interactor<Int>() {
    override suspend fun doWork(params: Int) {
        topicRepository.refresh()
    }
}