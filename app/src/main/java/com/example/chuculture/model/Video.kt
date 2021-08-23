package com.example.chuculture.model
import com.google.gson.annotations.SerializedName


data class Video(
    @SerializedName("address")
    val address: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)