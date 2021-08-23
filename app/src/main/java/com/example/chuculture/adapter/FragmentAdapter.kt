package com.example.chuculture.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chuculture.fragment.ImageTextFragment
import com.example.chuculture.fragment.VideoFragment

class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount()=2

    override fun createFragment(position: Int): Fragment {
        return if (position==0) ImageTextFragment()
        else VideoFragment()
    }
}