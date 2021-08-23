package com.example.chuculture.repository

import com.example.chuculture.network.NetWorkClient

class MuseumRepository {
    suspend fun getMuseumList()=NetWorkClient.createNetWorkApi().getMuseumList()

    suspend fun getMuseumById(id:Int)=NetWorkClient.createNetWorkApi().getMuseumById(id)
}