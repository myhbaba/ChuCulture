package com.example.chuculture.model

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("code")
    val code:Int,
    @SerializedName("data")
    val data:T?,
    @SerializedName("message")
    val message:String?
)
