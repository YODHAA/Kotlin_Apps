package com.example.fragment_component.api

import com.example.fragment_component.data.NewsArticles
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines?country=in&apiKey=2b7de8d0cf28444b8adf574d8127f327")
    fun getTopHighlights():Call<NewsArticles>

    @GET("top-headlines?country=in&category=sports&apiKey=2b7de8d0cf28444b8adf574d8127f327")
    fun getSportsNews():Call<NewsArticles>

    @GET("top-headlines?country=in&category=business&apiKey=2b7de8d0cf28444b8adf574d8127f327")
    fun getBussinessNews():Call<NewsArticles>

}