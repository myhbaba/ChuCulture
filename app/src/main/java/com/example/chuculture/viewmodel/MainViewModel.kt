package com.example.chuculture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {



    fun updateData(){
        viewModelScope.launch {
            repeat(10){
                delay(4000L)
            }
        }
    }
}