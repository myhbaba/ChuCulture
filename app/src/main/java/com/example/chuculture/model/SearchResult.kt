package com.example.chuculture.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("collectionForIndexDtoList")
    val relicList:List<Relic>?,
    @SerializedName("personForSearchDtoList")
    val heroList:List<Hero>?
)