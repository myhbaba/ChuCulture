package com.example.chuculture.model
import com.google.gson.annotations.SerializedName


data class Exhibition(
    @SerializedName("themeCollection")
    val themeCollection: ThemeCollection,
    @SerializedName("themeCollectionImgDtoList")
    val themeCollectionImgs: List<ThemeCollectionImg>
)
