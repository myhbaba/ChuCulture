package com.example.chuculture.network

import com.example.chuculture.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface NetWorkApi {

    @GET("/chuculture/index")
    suspend fun getIndex():Response<Index>

    @GET("chuculture/themecollection")
    suspend fun getAllThemeCollection(): Response<List<ThemeCollection>>

    @GET("chuculture/themecollection/detail")
    suspend fun getExhibitionById(@Query("id")id:Int):Response<ThemeCollection>

    @GET("chuculture/collection/refresh")
    suspend fun getUpdateRelic():Response<List<Relic>>

    @GET("chuculture/collection/update")
    suspend fun getMoreRelic(@Query("id")id:Int):Response<List<Relic>>

    @GET("chuculture/collection/detail")
    suspend fun getRelicById(@Query("id")id: Int):Response<Relic>

    @GET("/chuculture/museum")
    suspend fun getMuseumList():Response<List<Museum>>

    @GET("/chuculture/museum/detail")
    suspend fun getMuseumById(@Query("id")id:Int):Response<MuseumDetail>

    @GET("/chuculture/video")
    suspend fun getVideoList():Response<List<Video>>

    @GET("/chuculture/collection/year")
    suspend fun getRelicByYear(@Query("year")year:String):Response<List<Relic>>

    @GET("/chuculture/collection/material")
    suspend fun getRelicByMaterial(@Query("material")material:String):Response<List<Relic>>

    @GET("/chuculture/search")
    suspend fun getSearchResult(@Query("search") keyword:String):Response<SearchResult>

    @GET("/chuculture/person")
    suspend fun getHero():Response<List<Hero>>

    @GET("/chuculture/person/detail")
    suspend fun getHeroById(@Query("id")id:Int):Response<Hero>

    @GET("/chuculture/collectionpaper")
    suspend fun getPaperById(@Query("id")id:Int):Response<Paper>

}