package com.josh_portfolio.newsapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.josh_portfolio.newsapp.MockData
import com.josh_portfolio.newsapp.MockData.getTimeAgo
import com.josh_portfolio.newsapp.R
import com.josh_portfolio.newsapp.models.TopNewsArticle
import com.josh_portfolio.newsapp.models.getAllArticleCategory
import com.josh_portfolio.newsapp.network.NewsManager

@Composable
fun Categories(onFetchCategory: (String) -> Unit = {}, newsManager: NewsManager) {
    val tabsItems = getAllArticleCategory()
    Column {
        LazyRow {
            items(tabsItems.size) {
                val category = tabsItems[it]
                CategoryTab(
                    category = category.categoryName,
                    onFetchCategory = onFetchCategory,
                    isSelected = newsManager.selectedCategory.value == category
                )
            }
        }

//        ArticleContent()
    }
}


@Composable
fun CategoryTab(category: String, isSelected: Boolean = false, onFetchCategory: (String) -> Unit) {
    val background =
        if (isSelected) colorResource(id = R.color.purple_200) else colorResource(id = R.color.purple_700)

    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 16.dp)
            .clickable {
                onFetchCategory(category)
            },
        shape = MaterialTheme.shapes.small,
        color = background
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }

}

@Composable
fun ArticleContent(articles: List<TopNewsArticle>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(articles) { article ->
            Card(
                modifier = modifier.padding(8.dp), border = BorderStroke(
                    2.dp, color = colorResource(id = R.color.purple_500)
                )
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                {
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = "",
                        modifier = modifier.size(100.dp)
                    )

                    Column(modifier = modifier.padding(8.dp)) {
                        Text(
                            text = article.title ?: "Not Available",
                            fontWeight = FontWeight.Bold,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Text(text = article.author ?: "Not Available")
                            Text(text = MockData.stringToDate(article.publishedAt ?: "2022-05-27T14:30:20").getTimeAgo())
                        }
                    }

                }
            }
        }
    }
}