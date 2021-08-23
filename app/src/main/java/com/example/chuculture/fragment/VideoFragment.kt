package com.example.chuculture.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chuculture.R
import com.example.chuculture.viewmodel.VideoViewModel
import com.example.chuculture.adapter.VideoAdapter

class VideoFragment : Fragment() {



    private val viewModel by viewModels<VideoViewModel>()
    private val videoAdapter=VideoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.video_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }



    private fun initView(view: View) {
        val ryVideo=view.findViewById<RecyclerView>(R.id.ry_video)
        val linearLayoutManager=LinearLayoutManager(activity)
        ryVideo.layoutManager=linearLayoutManager
        ryVideo.adapter=videoAdapter

        viewModel.response.observe(viewLifecycleOwner,{
            if (it.code==1&&it.data!=null){
                videoAdapter.submitList(it.data)
            }
        })
    }

    private fun initData() {
        viewModel.getVideoList()
    }




}