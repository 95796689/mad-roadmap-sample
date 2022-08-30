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
import com.example.base_ui_compose.ui.LoginButton
import com.example.base_ui_compose.ui.RefreshButton
import com.example.base_ui_compose.ui.SwipeDismissSnackbarHost

@Composable
fun Discover(
    loginAction: () -> Unit
) {
    Discover(
        loginAction = loginAction,
        viewModel = hiltViewModel(),
    )
}

@Composable
internal fun Discover(
    loginAction: () -> Unit,
    viewModel: DiscoverViewModel
) {
    val viewState by rememberStateWithLifecycle(viewModel.state)

    Discover(
        state = viewState,
        loginAction = loginAction,
        isUserLogin = {viewModel.isUserLogin()},
        onMessageShown = { viewModel.clearMessage(it) }
    )
}

@Composable
internal fun Discover(
    state: DiscoverViewState,
    loginAction: () -> Unit,
    isUserLogin: () -> Boolean,
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
                onLoginActionClick = loginAction,
                isUserLogin = isUserLogin,
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
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
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
    onLoginActionClick: () -> Unit,
    isUserLogin: () -> Boolean,
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
            if (!isUserLogin()) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    LoginButton(onClick = onLoginActionClick)
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
                Text(text = it, style = MaterialTheme.typography.subtitle1)
            }

            Spacer(Modifier.height(4.dp))

            topic.content?.let {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = it, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}
