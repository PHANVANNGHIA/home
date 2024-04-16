package com.bytesthetic.shoesapp.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.bytesthetic.shoesapp.route.Routes

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomAppBar(
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Constants.BottomNavItems.forEach { navItem ->
                    IconButton(onClick = {navController.navigate(navItem.route)}) {
                        Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                    }
                }
            }
        }
    )
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route:String,
)

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = Routes.Home.route
        ),
        BottomNavItem(
            label = "Search",
            icon = Icons.Filled.Search,
            route = Routes.Search.route
        ),
        BottomNavItem(
            label = "Profile",
            icon = Icons.Filled.Person,
            route = Routes.Profile.route
        )
    )
}