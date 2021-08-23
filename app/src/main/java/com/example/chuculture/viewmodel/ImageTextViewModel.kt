package com.example.chuculture.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuculture.model.Index
import com.example.chuculture.model.Response
import com.example.chuculture.repository.IndexRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ImageTextViewModel : ViewModel() {
    private val repository=IndexRepository()
    val response=MutableLiveData<Response<Index>>()


    fun getIndex(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                response.postValue(repository.getIndex())
            }catch (e:Exception){
                Log.e("index","error")
                response.postValue(Response(0,null,null))
            }
        }
    }
}