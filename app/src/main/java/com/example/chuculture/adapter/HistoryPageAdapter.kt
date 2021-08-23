package com.example.chuculture.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chuculture.HistoryDataStore
import com.example.chuculture.fragment.HistoryFragment


class HistoryPageAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    companion object{
        private val dataStore=HistoryDataStore()
        fun getData()= dataStore.generateData()
    }

    override fun getItemCount()=4

    override fun createFragment(position: Int)=HistoryFragment.newInstance(getData()[position])

}