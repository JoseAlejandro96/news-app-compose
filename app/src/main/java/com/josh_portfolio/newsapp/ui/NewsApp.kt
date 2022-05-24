package com.josh_portfolio.newsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josh_portfolio.newsapp.ui.screen.DetailScreen
import com.josh_portfolio.newsapp.ui.screen.TopNews

@Composable
fun NewsApp() {
    Navigation()
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "TopNews"
    ){
        composable("TopNews"){
            TopNews(navController = navController)
        }
        composable("Detail"){
            DetailScreen(navController = navController)
        }
    }
}