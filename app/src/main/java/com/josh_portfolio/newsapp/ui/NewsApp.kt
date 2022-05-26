package com.josh_portfolio.newsapp.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josh_portfolio.newsapp.BottomMenuScreen
import com.josh_portfolio.newsapp.MockData
import com.josh_portfolio.newsapp.components.BottomMenu
import com.josh_portfolio.newsapp.models.TopNewsArticle
import com.josh_portfolio.newsapp.network.NewsManager
import com.josh_portfolio.newsapp.ui.screen.Categories
import com.josh_portfolio.newsapp.ui.screen.DetailScreen
import com.josh_portfolio.newsapp.ui.screen.Sources
import com.josh_portfolio.newsapp.ui.screen.TopNews

@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController = navController, scrollState = scrollState)
}

@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState) {
    Scaffold(bottomBar = { BottomMenu(navController = navController) }) {
        Navigation(navController = navController, scrollState = scrollState, paddingValues = it)
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    paddingValues: PaddingValues,
    newsManager: NewsManager = NewsManager()
) {
    val articles = newsManager.newsResponse.value.articles
    Log.d("news", "$articles")

    articles?.let {
        NavHost(
            navController = navController,
            startDestination = BottomMenuScreen.TopNews.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            bottomNavigation(navController = navController, articles)

            composable(
                "Detail/{index}",
                arguments = listOf(navArgument("index") { type = NavType.IntType })
            ) { navBackStackEntry ->
                val index = navBackStackEntry.arguments?.getInt("index")
                index?.let {
                    val article = articles[index]
                    DetailScreen(
                        scrollState = scrollState,
                        article = article,
                        navController = navController
                    )
                }
            }
        }
    }

}

fun NavGraphBuilder.bottomNavigation(navController: NavController, articles: List<TopNewsArticle>) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController, articles = articles)
    }
    composable(BottomMenuScreen.Categories.route) {
        Categories()
    }
    composable(BottomMenuScreen.Sources.route) {
        Sources()
    }
}