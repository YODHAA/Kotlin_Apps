package com.example.fragment_component.data


import com.google.gson.annotations.SerializedName

data class NewsArticles(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String, // ok
    @SerializedName("totalResults")
    val totalResults: Int // 38
)