package com.example.chuculture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuculture.model.Hero
import com.example.chuculture.model.Response
import com.example.chuculture.repository.HeroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HeroDetailViewModel:ViewModel() {
    private val repository=HeroRepository()
    val response=MutableLiveData<Response<Hero>>()
    fun getHeroById(id:Int){
        viewModelScope.launch (Dispatchers.IO){
            try {
                response.postValue(repository.getHeroById(id))
            }catch (e:Exception){
                response.postValue(Response(0,null,null))
            }
        }
    }
}