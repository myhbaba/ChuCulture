package com.example.chuculture.model
import com.google.gson.annotations.SerializedName


data class Relic(
    @SerializedName("abstract1")
    val abstract1: String,
    @SerializedName("abstract2")
    val abstract2: String?,
    @SerializedName("abstract3")
    val abstract3: String?,
    @SerializedName("collectionPaperForIndexDtoList")
    val paperList: List<Paper>?,
    @SerializedName("era")
    val era: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("material")
    val material: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("pianoStyle")
    val pianoStyle: String?,
    @SerializedName("seal")
    val seal: String?,
    @SerializedName("signature")
    val signature: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("year")
    val year: String?
)