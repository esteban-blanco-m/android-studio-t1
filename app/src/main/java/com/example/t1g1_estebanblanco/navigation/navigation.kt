package com.example.t1g1_estebanblanco.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.t1g1_estebanblanco.screens.HomeScreen
import androidx.navigation.compose.composable
import com.example.t1g1_estebanblanco.screens.GuessGame
import androidx.navigation.compose.rememberNavController
import com.example.t1g1_estebanblanco.screens.Drivers
import com.example.t1g1_estebanblanco.screens.DriversDetail

enum class AppScreens {
    Home, GuessGame, Drivers, DriversDetail
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Home.name) {
        composable(route = AppScreens.Home.name) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.GuessGame.name) {
            GuessGame(navController)
        }
        composable(route = AppScreens.Drivers.name) {
            Drivers(navController)
        }

        composable(
            route = "${AppScreens.DriversDetail.name}/{number}/{name}/{acronym}/{team}/{image}/{teamColor}"
        ) { backStackEntry ->
            val number = backStackEntry.arguments?.getString("number")
            val name = backStackEntry.arguments?.getString("name")
            val acronym = backStackEntry.arguments?.getString("acronym")
            val team = backStackEntry.arguments?.getString("team")
            val image = backStackEntry.arguments?.getString("image")
            val teamColor = backStackEntry.arguments?.getString("teamColor")

            DriversDetail(
                number = number,
                name = name,
                acronym = acronym,
                team = team,
                imageUrl = image,
                teamColor = teamColor,
                controller = navController
            )
        }
    }
}