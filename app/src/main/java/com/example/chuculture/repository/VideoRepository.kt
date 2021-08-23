package com.example.chuculture.repository

import com.example.chuculture.network.NetWorkClient

class VideoRepository {
    suspend fun getVideoList()=NetWorkClient.createNetWorkApi().getVideoList()
}