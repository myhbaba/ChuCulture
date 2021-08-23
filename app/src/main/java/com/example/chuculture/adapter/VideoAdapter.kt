package com.example.chuculture.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.example.chuculture.R
import com.example.chuculture.Utils.loadNormal
import com.example.chuculture.model.Video
import com.example.chuculture.network.NetWorkClient.BASE_VIDEO_URL

class VideoAdapter: ListAdapter<Video, VideoAdapter.ViewHolder>(VideoDiff()) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object{
        class VideoDiff:DiffUtil.ItemCallback<Video>(){
            override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem.id==newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.video_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jzVideo=holder.itemView.findViewById<JzvdStd>(R.id.jz_video)
        jzVideo.setUp(BASE_VIDEO_URL+getItem(position).address, getItem(position).name)
        jzVideo.posterImageView.loadNormal(getItem(position).image)
    }
}