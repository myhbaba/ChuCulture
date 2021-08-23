package com.example.chuculture.repository

import com.example.chuculture.network.NetWorkClient

class HeroRepository {
    suspend fun getHero()=NetWorkClient.createNetWorkApi().getHero()

    suspend fun getHeroById(id:Int)=NetWorkClient.createNetWorkApi().getHeroById(id)
}