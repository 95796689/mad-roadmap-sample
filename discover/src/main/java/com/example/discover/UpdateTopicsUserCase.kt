package com.example.discover

import com.dropbox.android.external.store4.StoreRequest
import com.example.base_android.AppCoroutineDispatchers
import com.example.base_android.Interactor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateTopicsUserCase @Inject constructor(
    private val topicRepository: TopicRepository,
    private val dispatchers: AppCoroutineDispatchers,
) : Interactor<Int>() {
    override suspend fun doWork(params: Int) {
        withContext(dispatchers.io) {
            topicRepository.stream(StoreRequest.fresh(0)).collect()
        }
    }
}