package com.example.chuculture.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuculture.R
import com.example.chuculture.Utils.loadBanner
import com.example.chuculture.Utils.loadLocalBanner
import com.example.chuculture.activity.ExhibitionActivity
import com.example.chuculture.model.Exhibition
import com.example.chuculture.model.ThemeCollection

class BannerAdapter(private val themeCollectionList:List<ThemeCollection>) : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = holder.itemView.findViewById<ImageView>(R.id.img)
        img.loadLocalBanner(themeCollectionList[position%themeCollectionList.size].image)
        val context=holder.itemView.context
        holder.itemView.setOnClickListener{
            val intent= Intent(context,ExhibitionActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

}