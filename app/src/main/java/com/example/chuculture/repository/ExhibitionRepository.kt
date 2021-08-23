package com.example.chuculture.repository

import com.example.chuculture.network.NetWorkClient

class ExhibitionRepository {

    suspend fun getAllThemeCollection()=NetWorkClient.createNetWorkApi().getAllThemeCollection()

    suspend fun getExhibitionById(id:Int)=NetWorkClient.createNetWorkApi().getExhibitionById(id)
}