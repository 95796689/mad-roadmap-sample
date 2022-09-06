package com.example.mine

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_data.topic.Topic
import com.example.android_data.user.User
import com.example.base_ui_compose.Layout
import com.example.base_ui_compose.bodyWidth
import com.example.base_ui_compose.rememberStateWithLifecycle
import com.example.base_ui_compose.theme.AppBarAlphas
import com.example.base_ui_compose.ui.LoginButton
import com.example.base_ui_compose.ui.PublishButton
import com.example.base_ui_compose.ui.SwipeDismissSnackbarHost

@Composable
fun MineScreen() {
    MineScreen(viewModel = hiltViewModel())
}

@Composable
fun MineScreen(
    viewModel: MineViewModel
) {

    val viewState by rememberStateWithLifecycle(viewModel.state)
    Scaffold(
        topBar = {
            MineAppBar(
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
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            ProfileSection(
                user = viewState?.user
            )
            Spacer(Modifier.height(30.dp))
            MyTopicsSection(
                topics = viewState?.topicList
            )
        }
    }
}

@Composable
fun MineAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface.copy(
            alpha = AppBarAlphas.translucentBarAlpha()
        ),
        contentColor = MaterialTheme.colors.onSurface,
        modifier = modifier,
        title = { Text(text = stringResource(R.string.mine_title)) },
    )
}

@Composable
fun ProfileSection(
    user: User?
) {
    Column(
        Modifier.padding(
            horizontal = Layout.bodyMargin,
            vertical = Layout.gutter,
        )
    ) {
        Text(text = stringResource(R.string.profile_title), style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "id: ${user?.userId}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "name: ${user?.name}")
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun MyTopicsSection(
    topics: List<Topic>?
) {
    Column(
        Modifier.padding(
            horizontal = Layout.bodyMargin,
            vertical = Layout.gutter,
        )
    ) {
        Text(text = stringResource(R.string.topics_title), style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(10.dp))
    }

    Divider(color = Color.Gray)

    LazyColumn(
        modifier = Modifier.bodyWidth()
    ) {
        topics?.let { topicList ->
            for (topic in topicList) {
                item {
                    MineTopicCard(
                        topic = topic
                    )
                    Divider(color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun MineTopicCard(
    topic: Topic
) {
    Column(
        Modifier.padding(
            horizontal = Layout.bodyMargin,
            vertical = Layout.gutter,
        )
    ) {
        Text(text = "title:${topic.title}", style = MaterialTheme.typography.subtitle1)

        Spacer(Modifier.height(4.dp))

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(text = "content:${topic.content}", style = MaterialTheme.typography.body1)
        }
    }
}