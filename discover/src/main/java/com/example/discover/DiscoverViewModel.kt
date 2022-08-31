package com.example.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.base_android.ObservableLoadingCounter
import com.example.base_android.UiMessageManager
import com.example.base_android.collectStatus
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DiscoverViewModel @Inject constructor(
    observeTopicUserCase: ObserveTopicUserCase
) : ViewModel() {
    private val topicLoadingStatus = ObservableLoadingCounter()
    private val uiMessageManager = UiMessageManager()

    val state: StateFlow<DiscoverViewState> = combine(
        topicLoadingStatus.observable,
        observeTopicUserCase.flow,
        uiMessageManager.message
    ) { topicLoadingStatus, topic, message ->
        DiscoverViewState(
            topicItems = topic,
            topicRefreshing = topicLoadingStatus,
            message = message
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DiscoverViewState.Empty,
    )

    init {
        observeTopicUserCase(0)
    }

    fun isUserLogin(): Boolean {
        val user = Firebase.auth.currentUser
        return user != null
    }

    fun clearMessage(id: Long) {
        viewModelScope.launch {
            uiMessageManager.clearMessage(id)
        }
    }
}