package com.satyayudha0077.assessment_mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.satyayudha0077.assessment_mobpro.ui.screen.RecycleBinScreen
import com.satyayudha0077.assessment_mobpro.ui.screen.AboutScreen
import com.satyayudha0077.assessment_mobpro.ui.screen.DetailScreen
import com.satyayudha0077.assessment_mobpro.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }

        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }

        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController = navController, id = null)
        }

        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument("idBuah") {
                    type = NavType.LongType
                }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong("idBuah") ?: 0L
            DetailScreen(navController = navController, id = id)
        }

        composable(route = Screen.RecycleBin.route) {
            RecycleBinScreen(navController)
        }
    }
}