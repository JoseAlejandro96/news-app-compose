package com.josh_portfolio.newsapp.network

import android.util.Log
import androidx.compose.runtime.*
import com.josh_portfolio.newsapp.models.ArticleCategory
import com.josh_portfolio.newsapp.models.TopNewsResponse
import com.josh_portfolio.newsapp.models.getArticleCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager {

    private val _newsResponse = mutableStateOf(TopNewsResponse())
    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember {
            _newsResponse
        }

    val selectedCategory: MutableState<ArticleCategory?> = mutableStateOf(null)


    init {
        getArticles()
    }

    private fun getArticles() {
        val service = Api
            .retrofitService.getTopArticles("us", Api.API_KEY)

        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful) {
                    _newsResponse.value = response.body()!!
                    Log.d("news", "${_newsResponse.value}")
                } else {
                    Log.d("error", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error", "${t.printStackTrace()}")
            }
        })
    }

    fun onSelectedCategoryChange(category: String) {
        val newCategory = getArticleCategory(category)
        selectedCategory.value = newCategory
    }
}