package com.belajarkotlin.easyparking.navigation

sealed class Screen(val route: String) {
    object ROOT: Screen(route = "root")
    object OnBoarding: Screen(route = "on_boarding_screen")
}