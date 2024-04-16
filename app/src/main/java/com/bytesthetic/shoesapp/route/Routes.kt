package com.bytesthetic.shoesapp.route

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Search : Routes("search")
    object Profile : Routes("profile")
}