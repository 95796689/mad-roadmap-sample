package com.example.mine

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_data.TopicRepository
import com.example.android_data.UserWithTopics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MineViewModel @Inject constructor(
    private val topicRepository: TopicRepository
) : ViewModel() {

    var mineState = mutableStateOf(UserWithTopics())
        private set

    fun getUserWithTopics() {
        viewModelScope.launch {
            mineState.value = topicRepository.getUserWithTopics()
        }
    }


}