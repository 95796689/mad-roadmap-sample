package com.example.publish

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_data.AddTopicSuccess
import com.example.android_data.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublishViewModel @Inject constructor(
    private val topicRepository: TopicRepository
) : ViewModel() {

    var uiState = mutableStateOf(PublishUiState())
        private set

    var publishSuccess = mutableStateOf(false)
        private set

    fun onTitleChange(newValue: String) {
        uiState.value = uiState.value.copy(title = newValue)
    }

    fun onContentChange(newValue: String) {
        uiState.value = uiState.value.copy(content = newValue)
    }

    fun addTopic() {
        viewModelScope.launch {
            val publishResult = topicRepository.addTopic(uiState.value.title, uiState.value.content)
            publishSuccess.value = publishResult is AddTopicSuccess
        }
    }
}