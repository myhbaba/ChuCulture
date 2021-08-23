package com.example.chuculture.repository

import com.example.chuculture.network.NetWorkClient

class RelicRepository {
    suspend fun getUpdateRelic()=NetWorkClient.createNetWorkApi().getUpdateRelic()
    suspend fun getMoreRelic(id:Int)=NetWorkClient.createNetWorkApi().getMoreRelic(id)
    suspend fun getRelicById(id:Int)=NetWorkClient.createNetWorkApi().getRelicById(id)
    suspend fun getRelicByYear(keyword:String)=NetWorkClient.createNetWorkApi().getRelicByYear(keyword)
    suspend fun getRelicByMaterial(keyword: String)=NetWorkClient.createNetWorkApi().getRelicByMaterial(keyword)
}