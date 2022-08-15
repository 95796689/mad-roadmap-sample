package com.example.discover

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_data.Topic
import com.example.base_ui_compose.Layout
import com.example.base_ui_compose.bodyWidth
import com.example.base_ui_compose.rememberStateWithLifecycle
import com.example.base_ui_compose.theme.AppBarAlphas
import com.example.base_ui_compose.ui.RefreshButton
import com.example.base_ui_compose.ui.SwipeDismissSnackbarHost

@Composable
fun Discover() {
    Discover(
        viewModel = hiltViewModel(),
    )
}

@Composable
internal fun Discover(
    viewModel: DiscoverViewModel,
) {
    val viewState by rememberStateWithLifecycle(viewModel.state)

    Discover(
        state = viewState,
        refresh = { viewModel.refresh() },
        onMessageShown = { viewModel.clearMessage(it) }
    )
}

@Composable
internal fun Discover(
    state: DiscoverViewState,
    refresh: () -> Unit,
    onMessageShown: (id: Long) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    state.message?.let { message ->
        LaunchedEffect(message) {
            scaffoldState.snackbarHostState.showSnackbar(message.message)
            // Notify the view model that the message has been dismissed
            onMessageShown(message.id)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DiscoverAppBar(
                refreshing = state.topicRefreshing,
                onRefreshActionClick = refresh,
                modifier = Modifier.fillMaxWidth()
            )
        },
        snackbarHost = { snackbarHostState ->
            SwipeDismissSnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .padding(horizontal = Layout.bodyMargin)
                    .fillMaxWidth()
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValue ->
        Scaffold(
            modifier = Modifier.padding(paddingValue)
        ) {
            LazyColumn(
                contentPadding = it,
                modifier = Modifier.bodyWidth(),
            ) {
                item {
                    Spacer(Modifier.height(Layout.gutter))
                }

                for (topic in state.topicItems) {
                    item {
                        TopicCard(topic = topic,
                            modifier = Modifier.fillMaxWidth())
                    }
                }

                item {
                    Spacer(Modifier.height(Layout.gutter))
                }
            }
        }
    }
}

@Composable
fun DiscoverAppBar(
    refreshing: Boolean,
    onRefreshActionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface.copy(
            alpha = AppBarAlphas.translucentBarAlpha()
        ),
        contentColor = MaterialTheme.colors.onSurface,
        modifier = modifier,
        title = { Text(text = stringResource(R.string.discover_title)) },
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                // This button refresh allows screen-readers, etc to trigger a refresh.
                // We only show the button to trigger a refresh, not to indicate that
                // we're currently refreshing, otherwise we have 4 indicators showing the
                // same thing.
                Crossfade(
                    targetState = refreshing,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) { isRefreshing ->
                    if (!isRefreshing) {
                        RefreshButton(onClick = onRefreshActionClick)
                    }
                }
            }
        },
    )
}

@Composable
private fun TopicCard(
    topic: Topic,
    modifier: Modifier = Modifier,
) {
    Surface(modifier) {
        Column(
            Modifier.padding(
                horizontal = Layout.bodyMargin,
                vertical = Layout.gutter,
            )
        ) {
            topic.title?.let {
                Text(text = it)
            }

            Spacer(Modifier.height(4.dp))

            topic.content?.let {
                Text(text = it)
            }
        }
    }
}
