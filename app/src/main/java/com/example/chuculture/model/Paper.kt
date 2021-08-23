package com.example.chuculture.model
import com.google.gson.annotations.SerializedName


data class Paper(
    @SerializedName("abstract1")
    val abstract1: String,
    @SerializedName("author")
    val author: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String?,
    @SerializedName("journals")
    val journals: String?,
    @SerializedName("keyword")
    val keyword: String?,
    @SerializedName("mechanism")
    val mechanism: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("time")
    val time: String?
)