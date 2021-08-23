package com.example.chuculture.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chuculture.DataIntentTrans.Companion.MUSEUM_ID
import com.example.chuculture.R
import com.example.chuculture.Utils.loadNormal
import com.example.chuculture.activity.MuseumDetailActivity
import com.example.chuculture.model.Museum
import com.example.chuculture.model.MuseumDetail

class MuseumListAdapter:ListAdapter<Museum,MuseumListAdapter.ViewHolder> (MuseumDiff()){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    companion object{
        class MuseumDiff: DiffUtil.ItemCallback<Museum>(){
            override fun areItemsTheSame(oldItem: Museum, newItem: Museum): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Museum, newItem: Museum): Boolean {
                return oldItem.id==newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.museum_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_museum_name).text=getItem(position).name

        val desc=if (getItem(position).introduction.length<15){
            getItem(position).introduction
        }else{
            getItem(position).introduction.substring(0,15)+"..."
        }
        holder.itemView.findViewById<TextView>(R.id.tv_museum_desc).text=desc
        holder.itemView.findViewById<ImageView>(R.id.img_museum).loadNormal(getItem(position).image)

        val context=holder.itemView.context
        holder.itemView.setOnClickListener {
            val intent= Intent(context,MuseumDetailActivity::class.java)
            intent.putExtra(MUSEUM_ID,getItem(position).id)
            context.startActivity(intent)
        }
    }

}