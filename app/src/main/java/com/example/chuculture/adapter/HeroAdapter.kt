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
import com.example.chuculture.DataIntentTrans.Companion.HERO_ID
import com.example.chuculture.R
import com.example.chuculture.Utils.loadNormal
import com.example.chuculture.activity.HeroDetailActivity
import com.example.chuculture.model.Hero

class HeroAdapter :ListAdapter<Hero,HeroAdapter.ViewHolder>(HeroDiff()){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object{
        class HeroDiff: DiffUtil.ItemCallback<Hero>(){
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem==newItem
            }
            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem.id==newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.hero_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.img_hero).loadNormal(getItem(position).image)
        holder.itemView.findViewById<TextView>(R.id.tv_hero_name).text=getItem(position).name
        holder.itemView.findViewById<TextView>(R.id.tv_hero_abstract).text=getItem(position).abstract1

        val context=holder.itemView.context
        holder.itemView.setOnClickListener{
            val intent= Intent(context,HeroDetailActivity::class.java)
            intent.putExtra(HERO_ID,getItem(position).id)
            context.startActivity(intent)
        }

    }
}