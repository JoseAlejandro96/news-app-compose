package com.josh_portfolio.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.josh_portfolio.newsapp.MockData
import com.josh_portfolio.newsapp.MockData.getTimeAgo
import com.josh_portfolio.newsapp.R
import com.josh_portfolio.newsapp.models.NewsData
import com.josh_portfolio.newsapp.models.TopNewsArticle

@Composable
fun TopNews(navController: NavController, articles: List<TopNewsArticle>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn {
            items(articles.size) { index ->
                TopNewsItem(article = articles[index], onNewsClick = {
                    navController.navigate("Detail/${index}")
                })
            }
        }
    }
}

@Composable
fun TopNewsItem(article: TopNewsArticle, onNewsClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onNewsClick()
            },
        elevation = 8.dp,

        ) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
//        error = ImageBitmap.imageResource(id = R.drawable.tech2),
//        placeholder = ImageBitmap.imageResource(id = R.drawable.tech2)
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = MockData.stringToDate(article.publishedAt!!).getTimeAgo(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(80.dp))
            Text(text = article.title!!, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TopNewsItem(
        article = TopNewsArticle(
            author = "Raja Razek, CNN",
            title = "'Tiger King' Joe Exotic says he has been diagnosed with aggressive form of prostate cancer - CNN",
            description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix docuseries \\\"Tiger King: Murder, Mayhem and Madness,\\\" has been diagnosed with an aggressive form of prostate cancer, according to a letter written by Maldonado.",
            publishedAt = "2021-11-04T05:35:21Z"
        )
    )
}