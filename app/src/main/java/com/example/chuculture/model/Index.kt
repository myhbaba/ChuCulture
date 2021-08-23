package com.example.chuculture.model

import com.google.gson.annotations.SerializedName

data class Index(
    @SerializedName("themeCollectionDtoList")
    val themeCollectionList: List<ThemeCollection>,
    @SerializedName("personDtoList")
    val heroList: List<Hero>,
    @SerializedName("museumForIndexDtoList")
    val museumList: List<Museum>,
    @SerializedName("collectionDtoList")
    val relicList: List<List<Relic>>
)
