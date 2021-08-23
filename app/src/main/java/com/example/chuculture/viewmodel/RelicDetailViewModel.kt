package com.example.chuculture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuculture.model.Relic
import com.example.chuculture.model.Response
import com.example.chuculture.repository.RelicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RelicDetailViewModel :ViewModel(){
    private val repository=RelicRepository()
    val response=MutableLiveData<Response<Relic>>()

    fun getRelicById(id:Int){
        viewModelScope.launch (Dispatchers.IO){
            try {
                response.postValue(repository.getRelicById(id))
            }catch (e:Exception){
                response.postValue(Response(0,null,null))
            }
        }
    }
}