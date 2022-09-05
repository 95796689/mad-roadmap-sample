package com.example.mine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_data.TopicRepository
import com.example.android_data.UserWithTopics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MineViewModel @Inject constructor(
    topicRepository: TopicRepository
) : ViewModel() {

    val state: StateFlow<UserWithTopics?> = topicRepository.observeUserWithTopics().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )
}