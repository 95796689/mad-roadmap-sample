package com.example.sad

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.discover.Discover


internal sealed class Screen(val route: String) {
    object Discover : Screen("discover")
    object Message : Screen("message")
    object Mine : Screen("mine")
}

//private sealed class LeafScreen(
//    private val route: String,
//) {
//    fun createRoute(root: Screen) = "${root.route}/$route"
//
//    object Discover : LeafScreen("discover")
//    object Message : LeafScreen("message")
//    object Mine : LeafScreen("mine")
//    object Account : LeafScreen("account")
//}

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
            )
        }
    }
}

private val NavDestination.hostNavGraph: NavGraph
    get() = hierarchy.first { it is NavGraph } as NavGraph