package com.belajarkotlin.easyparking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.belajarkotlin.easyparking.authentication.Login
import com.belajarkotlin.easyparking.authentication.Register
import com.belajarkotlin.easyparking.onBoarding.OnBoarding

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController, 
        route = Screen.ROOT.route,
        startDestination = Screen.OnBoarding.route
    ) {

        composable(
            route = Screen.OnBoarding.route
        ) {
            OnBoarding(navHostController = navHostController)
        }

        composable(
            route = Screen.Register.route
        ) {
            Register(navHostController = navHostController)
        }

        composable(
            route = Screen.Login.route
        ) {
            Login(navHostController = navHostController)
        }

    }
}