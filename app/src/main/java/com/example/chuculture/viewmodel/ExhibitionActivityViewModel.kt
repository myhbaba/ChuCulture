package com.example.chuculture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuculture.model.Exhibition
import com.example.chuculture.model.Response
import com.example.chuculture.model.ThemeCollection
import com.example.chuculture.repository.ExhibitionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ExhibitionActivityViewModel:ViewModel() {
    val response=MutableLiveData<Response<List<ThemeCollection>>>()
    val respDetailById=MutableLiveData<Response<ThemeCollection>>()
    private val repository=ExhibitionRepository()

    fun getAllThemeCollection(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                response.postValue(repository.getAllThemeCollection())
            }catch (e:Exception){
                e.printStackTrace()
                response.postValue(Response(0,null,null))
            }
        }
    }

    fun getThemeCollectionById(id:Int){
        viewModelScope.launch (Dispatchers.IO){
            try {
                respDetailById.postValue(repository.getExhibitionById(id))
            }catch (e:Exception){
                e.printStackTrace()
                respDetailById.postValue(Response(0,null,null))
            }
        }
    }
}