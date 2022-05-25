package com.josh_portfolio.newsapp.models

import com.josh_portfolio.newsapp.R

data class NewsData(
    val id: Int,
    val image: Int = R.drawable.tech2,
    val author: String,
    val title: String,
    val description: String,
    val publishedAt: String
)