package com.example.chuculture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuculture.model.Museum
import com.example.chuculture.model.MuseumDetail
import com.example.chuculture.model.Response
import com.example.chuculture.repository.MuseumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MuseumDetailViewModel: ViewModel() {
    private val repository= MuseumRepository()
    val response= MutableLiveData<Response<MuseumDetail>>()

    fun getMuseumById(id:Int){
        viewModelScope.launch (Dispatchers.IO){
            try {
                response.postValue(repository.getMuseumById(id))
            }catch (e: Exception){
                response.postValue(Response(0,null,null))
            }
        }
    }
}