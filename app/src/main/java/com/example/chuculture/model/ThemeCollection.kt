package com.example.chuculture.model

import com.google.gson.annotations.SerializedName

data class ThemeCollection (
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("introduction")
    val introduction: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("themeCollectionImgDtoList")
    val imageList:List<ThemeCollectionImg>?
)