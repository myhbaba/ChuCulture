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
import com.example.chuculture.DataIntentTrans
import com.example.chuculture.DataIntentTrans.Companion.RELIC_ID
import com.example.chuculture.R
import com.example.chuculture.Utils.loadNormal
import com.example.chuculture.activity.RelicDetailActivity
import com.example.chuculture.activity.RelicTypeActivity
import com.example.chuculture.model.Relic
import com.google.android.material.chip.Chip

class RelicAdapter :ListAdapter<Relic,RelicAdapter.ViewHolder>(RelicDiff()){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object{
        class RelicDiff:DiffUtil.ItemCallback<Relic>(){
            override fun areItemsTheSame(oldItem: Relic, newItem: Relic): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Relic, newItem: Relic): Boolean {
                return oldItem.id==newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.relic_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var desc= getItem(position).abstract1

        if (desc.length>13){
            desc=desc.substring(0,13)+".."
        }

        holder.itemView.findViewById<TextView>(R.id.tv_relic_desc).text=desc

        var name=getItem(position).name

        if (name.length>8){
            name=name.substring(0,8)+".."
        }

        holder.itemView.findViewById<TextView>(R.id.tv_relic_name).text=name
        holder.itemView.findViewById<ImageView>(R.id.img_relic).loadNormal(getItem(position).image)
        holder.itemView.findViewById<Chip>(R.id.chip_relic_year).text=getItem(position).year
        holder.itemView.findViewById<Chip>(R.id.chip_relic_material).text=getItem(position).material

        val context=holder.itemView.context
        holder.itemView.setOnClickListener {
            val intent=Intent(context,RelicDetailActivity::class.java)
            intent.putExtra(RELIC_ID,getItem(position).id)
            context.startActivity(intent)
        }

        holder.itemView.findViewById<Chip>(R.id.chip_relic_year).setOnClickListener {
            val intent=Intent(context, RelicTypeActivity::class.java)
            intent.putExtra(DataIntentTrans.RELIC_START_TYPE, DataIntentTrans.RELIC_TYPE_YEAR)
            intent.putExtra(DataIntentTrans.RELIC_QUERY_KEYWORD,getItem(position).year)
            context.startActivity(intent)
        }
        holder.itemView.findViewById<Chip>(R.id.chip_relic_material).setOnClickListener {
            val intent=Intent(context,RelicTypeActivity::class.java)
            intent.putExtra(DataIntentTrans.RELIC_START_TYPE, DataIntentTrans.RELIC_TYPE_MATERIAL)
            intent.putExtra(DataIntentTrans.RELIC_QUERY_KEYWORD,getItem(position).material)
            context.startActivity(intent)
        }
    }

}