package com.evgeniykim.poststest.common

import com.evgeniykim.poststest.retrofit.RetrofitClient
import com.evgeniykim.poststest.retrofit.RetrofitServices
import retrofit2.create

object Common {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val retrofitServices: RetrofitServices
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}