package com.example.chuculture.model

import com.google.gson.annotations.SerializedName

data class Museum(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("image")
    val image:String,
    @SerializedName("introduction")
    val introduction:String
)