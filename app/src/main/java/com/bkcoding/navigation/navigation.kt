package com.bkcoding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bkcoding.MainScreen
import com.bkcoding.paging3compose.DetailScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        composable("MainScreen") {
            MainScreen(navController)
        }

        composable("DetailScreen/{name}/{description}/{url}") {
            val name = it.arguments?.getString("name")
            val description = it.arguments?.getString("description")
            val url = it.arguments?.getString("url")

            DetailScreen(navController, name = name, description = description, url = url)
        }

    }
}