package com.example.chuculture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chuculture.OnItemClickListener
import com.example.chuculture.R
import com.example.chuculture.Utils.loadLocalBanner
import com.example.chuculture.model.ThemeCollection

class ExhibitionAdapter (private val onItemClickListener: OnItemClickListener):
    ListAdapter<ThemeCollection, ExhibitionAdapter.ViewHolder>(ExhibitionDiff()) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        class ExhibitionDiff : DiffUtil.ItemCallback<ThemeCollection>() {
            override fun areItemsTheSame(
                oldItem: ThemeCollection,
                newItem: ThemeCollection
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ThemeCollection,
                newItem: ThemeCollection
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.exhibition_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imgExhibition = holder.itemView.findViewById<ImageView>(R.id.img_exhibition)
        imgExhibition.loadLocalBanner(getItem(position).image)
        holder.itemView.setOnClickListener{
            onItemClickListener.click(getItem(position))
        }
    }
}