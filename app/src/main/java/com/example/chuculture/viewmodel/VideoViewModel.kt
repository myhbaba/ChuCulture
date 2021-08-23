package com.example.chuculture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuculture.model.Response
import com.example.chuculture.model.Video
import com.example.chuculture.repository.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class VideoViewModel : ViewModel() {
    private val repository=VideoRepository()
    val response=MutableLiveData<Response<List<Video>>>()

    fun getVideoList(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                response.postValue(repository.getVideoList())
            }catch (e:Exception){
                response.postValue(Response(0,null,null))
            }
        }
    }
}