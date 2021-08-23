package com.example.chuculture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuculture.model.Response
import com.example.chuculture.model.SearchResult
import com.example.chuculture.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel:ViewModel() {
    private val repository= SearchRepository()
    val response=MutableLiveData<Response<SearchResult>>()

    fun getSearchRelic(keyword:String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                response.postValue(repository.getSearchResult(keyword))
            }catch (e:Exception){
                response.postValue(Response(0,null,null))
            }
        }
    }
}