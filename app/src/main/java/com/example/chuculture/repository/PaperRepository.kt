package com.example.chuculture.repository

import com.example.chuculture.network.NetWorkClient

class PaperRepository {
    suspend fun getPaperById(id:Int)=NetWorkClient.createNetWorkApi().getPaperById(id)
}