package com.example.india_flight.services

import com.example.india_flight.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationService {

    // Point to web service end point URL's define function for that
     @GET("destination")
    fun getDestinationList(): Call<List<Destination>>

    @GET("destination")
    fun getDestination(@Query("country")country: String):Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

    @POST("destination")
    fun addDestination(@Body newDestination: Destination): Call<Destination>

    @FormUrlEncoded
    @PUT("destination/{id}")
    fun updateDestination(
        @Path("id") id: Int,
        @Field("city") city: String,
        @Field("description") desc: String,
        @Field("country") country: String
    ): Call<Destination>

    @DELETE("destination/{id}")
    fun deleteDestination(@Path("id") id: Int): Call<Unit>

}