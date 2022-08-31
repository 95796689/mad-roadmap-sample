package com.example.publish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_data.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublishViewModel @Inject constructor(
    private val topicRepository: TopicRepository
) : ViewModel() {
    fun addTopic(title: String, content: String) {
        viewModelScope.launch {
            topicRepository.addTopic(title, content)
        }
    }
}