package com.example.discover

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.base_ui_compose.rememberStateWithLifecycle
import com.example.base_ui_compose.theme.AppBarAlphas
import com.example.base_ui_compose.ui.RefreshButton
import com.example.base_ui_compose.ui.UserProfileButton

@Composable
fun Discover(
    //openUser: () -> Unit,
) {
//    Discover(
//        viewModel = hiltViewModel(),
//        openUser = openUser,
//    )
}

@Composable
internal fun Discover(
    //viewModel: DiscoverViewModel,
    openUser: () -> Unit,
) {
    //val viewState by rememberStateWithLifecycle(viewModel.state)

    Discover(
        //state = viewState,
        //refresh = { viewModel.refresh() },
        openUser = openUser,
    )
}

//@Composable
//internal fun Discover(
//    //state: DiscoverViewState,
////    refresh: () -> Unit,
////    openUser: () -> Unit,
//) {
//    //val scaffoldState = rememberScaffoldState()
//}

//    Scaffold(
//        scaffoldState = scaffoldState,
////        topBar = {
////            DiscoverAppBar(
////                loggedIn = state.authState == TraktAuthState.LOGGED_IN,
////                user = state.user,
////                refreshing = state.refreshing,
////                onRefreshActionClick = refresh,
////                onUserActionClick = openUser,
////                modifier = Modifier.fillMaxWidth()
////            )
////        },
////        snackbarHost = { snackbarHostState ->
////            SwipeDismissSnackbarHost(
////                hostState = snackbarHostState,
////                modifier = Modifier
////                    .padding(horizontal = Layout.bodyMargin)
////                    .fillMaxWidth()
////            )
////        },
//        modifier = Modifier.fillMaxSize(),
//    ) {
//        Scaffold() {
//
//        }
//    }
//}

@Composable
fun DiscoverAppBar(
    loggedIn: Boolean,
//    refreshing: Boolean,
//    onRefreshActionClick: () -> Unit,
    onUserActionClick: () -> Unit,
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
//                Crossfade(
//                    //targetState = refreshing,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                ) { isRefreshing ->
//                    if (!isRefreshing) {
//                        RefreshButton(onClick = onRefreshActionClick)
//                    }
//                }
            }

            UserProfileButton(
                loggedIn = loggedIn,
                onClick = onUserActionClick,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        },
    )
}
