package com.example.chuculture.model

import com.google.gson.annotations.SerializedName

data class ThemeCollectionImg(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)