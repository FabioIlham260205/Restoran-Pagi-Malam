package com.example.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MenuScreen
import com.example.ui.screens.DetailMenuScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.EditProfileScreen

object RestaurantDestinations {
    const val HOME = "home"
    const val MENU = "menu"
    const val DETAIL = "detail/{menuId}"
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"

    fun detailRoute(menuId: String) = "detail/$menuId"
}

@Composable
fun RestaurantNavHost(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RestaurantDestinations.HOME,
        modifier = modifier,
        // Fluid transition animations matching Material 3 patterns
        enterTransition = {
            slideInHorizontally(initialOffsetX = { 300 }, animationSpec = tween(400)) + fadeIn(animationSpec = tween(400))
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -300 }, animationSpec = tween(400)) + fadeOut(animationSpec = tween(400))
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -300 }, animationSpec = tween(400)) + fadeIn(animationSpec = tween(400))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { 300 }, animationSpec = tween(400)) + fadeOut(animationSpec = tween(400))
        }
    ) {
        // [1] HomeScreen
        composable(RestaurantDestinations.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToMenu = { navController.navigate(RestaurantDestinations.MENU) },
                onNavigateToProfile = { navController.navigate(RestaurantDestinations.PROFILE) },
                onNavigateToDetail = { menuId -> navController.navigate(RestaurantDestinations.detailRoute(menuId)) }
            )
        }

        // [2] MenuScreen
        composable(RestaurantDestinations.MENU) {
            MenuScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToDetail = { menuId -> navController.navigate(RestaurantDestinations.detailRoute(menuId)) }
            )
        }

        // [3] DetailMenuScreen (with nav argument)
        composable(
            route = RestaurantDestinations.DETAIL,
            arguments = listOf(navArgument("menuId") { type = NavType.StringType })
        ) { backStackEntry ->
            val menuId = backStackEntry.arguments?.getString("menuId") ?: ""
            DetailMenuScreen(
                menuId = menuId,
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // [4] ProfileScreen
        composable(RestaurantDestinations.PROFILE) {
            ProfileScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToEditProfile = { navController.navigate(RestaurantDestinations.EDIT_PROFILE) }
            )
        }

        // [5] EditProfileScreen
        composable(RestaurantDestinations.EDIT_PROFILE) {
            EditProfileScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
