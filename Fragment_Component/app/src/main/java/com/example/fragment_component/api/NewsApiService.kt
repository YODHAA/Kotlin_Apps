package com.example.fragment_component.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// singleton class
object NewsApiService {

    fun create():NewsApi{
        val retrofit=Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service=retrofit.create(NewsApi::class.java)

        return service
    }

}