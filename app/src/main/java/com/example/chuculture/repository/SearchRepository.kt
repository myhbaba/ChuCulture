package com.example.chuculture.repository

import com.example.chuculture.network.NetWorkClient

class SearchRepository {
    suspend fun getSearchResult(keyword:String)=NetWorkClient.createNetWorkApi().getSearchResult(keyword)
}