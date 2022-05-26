package com.josh_portfolio.newsapp.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.josh_portfolio.newsapp.R

@Composable
fun Categories() {
    Text(text = "Categories Screen")
}

@Composable
fun CategoryTab(category: String, isSelected: Boolean = false, onFetchCategory: (String) -> Unit) {
    val background = if(isSelected) colorResource(id = R.color.purple_200) else colorResource(id = R.color.purple_700)


}