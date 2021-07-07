package com.evgeniykim.poststest.retrofit

import com.evgeniykim.poststest.Model
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitServices {
    @GET("posts")
    fun getNewsList(): Call<MutableList<Model>>

    @FormUrlEncoded
    @POST("/posts")
    fun postDataFields(@Field("title") title: String, @Field("body") body: String, @Field("userId") userId: Int): Call<Model>
}