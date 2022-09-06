package com.example.sad

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.discover.Discover
import com.example.mine.MineScreen
import com.example.publish.PublishScreen
import com.example.user_auth.AuthScreen


internal sealed class Screen(val route: String) {
    object Discover : Screen("discover")
    object Mine : Screen("mine")
}

internal sealed class LeafScreen(val route: String) {
    object Login : LeafScreen("login")
    object Publish : LeafScreen("publish")
}

@ExperimentalAnimationApi
@Composable
internal fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Discover.route,
    ) {
        composable(
            route = Screen.Discover.route
        ) {
            Discover(
                loginAction = {
                    navController.navigate(LeafScreen.Login.route)
                },
                publishAction = {
                    navController.navigate(LeafScreen.Publish.route)
                }
            )
        }

        composable(
            route = Screen.Mine.route
        ) {
            MineScreen()
        }

        composable(
            route = LeafScreen.Login.route
        ) {
            AuthScreen(
                navigateUp = navController::navigateUp
            )
        }

        composable(
            route = LeafScreen.Publish.route
        ) {
            PublishScreen(
                navigateUp = navController::navigateUp
            )
        }
    }
}

private val NavDestination.hostNavGraph: NavGraph
    get() = hierarchy.first { it is NavGraph } as NavGraph