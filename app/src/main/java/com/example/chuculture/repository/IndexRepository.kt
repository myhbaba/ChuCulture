package com.example.chuculture.repository

import com.example.chuculture.network.NetWorkClient

class IndexRepository {
    suspend fun getIndex()=NetWorkClient.createNetWorkApi().getIndex()
}