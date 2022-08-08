package com.example.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class DiscoverViewModel : ViewModel() {
//    val state: StateFlow<DiscoverViewState> = combine(
//        trendingLoadingState.observable,
//        popularLoadingState.observable,
//        recommendedLoadingState.observable,
//        observeTrendingShows.flow,
//        observePopularShows.flow,
//        observeRecommendedShows.flow,
//        observeNextShowEpisodeToWatch.flow,
//        observeTraktAuthState.flow,
//        observeUserDetails.flow,
//        uiMessageManager.message,
//    ) { trendingLoad, popularLoad, recommendLoad, trending, popular, recommended, nextShow,
//        authState, user, message,
//        ->
//        DiscoverViewState(
//            user = user,
//            authState = authState,
//            trendingItems = trending,
//            trendingRefreshing = trendingLoad,
//            popularItems = popular,
//            popularRefreshing = popularLoad,
//            recommendedItems = recommended,
//            recommendedRefreshing = recommendLoad,
//            nextEpisodeWithShowToWatched = nextShow,
//            message = message,
//        )
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = DiscoverViewState.Empty,
//    )

//    fun refresh(fromUser: Boolean = true) {
//    }
}