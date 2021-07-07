package com.evgeniykim.poststest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Model (
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("body")
    @Expose
    var body: String,
    @SerializedName("userId")
    @Expose
    var userId: Int,
    var id: Int,
    var imageUrl: String
)