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
//                openUser = {
//
//                },
            )
        }
        //addDiscoverTopLevel(navController)
//        addMessageTopLevel(navController)
//        addMineTopLevel(navController)
    }
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultTiviEnterTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry,
): EnterTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    // If we're crossing nav graphs (bottom navigation graphs), we crossfade
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeIn()
    }
    // Otherwise we're in the same nav graph, we can imply a direction
    return fadeIn() + slideIntoContainer(AnimatedContentScope.SlideDirection.Start)
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultTiviExitTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry,
): ExitTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    // If we're crossing nav graphs (bottom navigation graphs), we crossfade
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeOut()
    }
    // Otherwise we're in the same nav graph, we can imply a direction
    return fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.Start)
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultTiviPopEnterTransition(): EnterTransition {
    return fadeIn() + slideIntoContainer(AnimatedContentScope.SlideDirection.End)
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultTiviPopExitTransition(): ExitTransition {
    return fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.End)
}

private val NavDestination.hostNavGraph: NavGraph
    get() = hierarchy.first { it is NavGraph } as NavGraph

//@ExperimentalAnimationApi
//private fun NavGraphBuilder.addDiscoverTopLevel(
//    navController: NavController,
//) {
//    navigation(
//        route = Screen.Discover.route,
//        startDestination = LeafScreen.Discover.createRoute(Screen.Discover),
//    ) {
//        addDiscover(navController, Screen.Discover)
//        addAccount(Screen.Discover)
//    }
//}

//@ExperimentalAnimationApi
//private fun NavGraphBuilder.addDiscover(
//    navController: NavController,
//    root: Screen,
//) {
//    composable(
//        route = Screen.Discover.route
//    ) {
//        Discover(
//            openUser = {
//                navController.navigate(LeafScreen.Account.createRoute(root))
//            },
//        )
//    }
//}

//@ExperimentalAnimationApi
//private fun NavGraphBuilder.addMessage(
//    navController: NavController,
//    root: Screen,
//) {
//    composable(
//        route = Screen.Message.route
//    ) {
//        Discover(
//            openUser = {
//                navController.navigate(LeafScreen.Account.createRoute(root))
//            },
//        )
//    }
//}

//@ExperimentalAnimationApi
//private fun NavGraphBuilder.addAccount(
//    root: Screen,
//) {
//    dialog(
//        route = LeafScreen.Account.createRoute(root),
//    ) {
//        LoginScreen()
//    }
//}