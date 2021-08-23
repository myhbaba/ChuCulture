package com.example.chuculture.model
import com.google.gson.annotations.SerializedName



data class Hero(
    @SerializedName("abstract1")
    val abstract1: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lyric")
    val lyric: String?,
    @SerializedName("name")
    val name: String
)