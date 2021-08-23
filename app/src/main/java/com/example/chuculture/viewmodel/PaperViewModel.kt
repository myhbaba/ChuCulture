package com.example.chuculture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuculture.model.Paper
import com.example.chuculture.model.Response
import com.example.chuculture.repository.PaperRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class PaperViewModel:ViewModel() {
    private val repository=PaperRepository()
    val response=MutableLiveData<Response<Paper>>()


    fun getPaperById(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                response.postValue(repository.getPaperById(id))
            }catch (e:Exception){
                response.postValue(Response(0,null,null))
            }
        }
    }
}