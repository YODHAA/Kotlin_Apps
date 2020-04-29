package com.example.india_flight.services

import android.os.Build
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object ServiceBuilder {

    // main base URL's written combined with DestinationService url's to give whole Link
    private const val URL = "https://globoflyapp.herokuapp.com/"

    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //Create OkHttp Client
    private val okHttp=OkHttpClient.Builder().addInterceptor(logger)

    // Create a RetroFit Builder
    private val builder=Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // Create the RetroFit Instance
    private val retrofit= builder.build()

    // will call this function via inner class object, by passing the Interface service class returns the class SB and
    // implements the  Interface Method

    fun<T>buildService(serviceType:Class<T>):T{
             return retrofit.create(serviceType)
    }



    // Create a Custom Interceptor to apply Headers application wide
    val headerInterceptor = object: Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {

            var request = chain.request()

            request = request.newBuilder()
                .addHeader("x-device-type", Build.DEVICE)
                .addHeader("Accept-Language", Locale.getDefault().language)
                .build()

            val response = chain.proceed(request)
            return response
        }
    }

}