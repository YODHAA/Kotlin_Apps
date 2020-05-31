package com.saurabh.yourworld.common

import com.saurabh.yourworld.Model.Results
import com.saurabh.yourworld.remote.IGoogleAPIService
import com.saurabh.yourworld.remote.RetrofitClient

object Common {

    private val GOOGLE_API_URL="https://maps.googleapis.com/"

    var currentResult:Results?=null

    val googleApiService:IGoogleAPIService
    get() = RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService::class.java)
}