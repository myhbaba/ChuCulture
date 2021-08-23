package com.example.chuculture.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chuculture.DataIntentTrans.Companion.RELIC_ID
import com.example.chuculture.R
import com.example.chuculture.Utils.loadNormal
import com.example.chuculture.activity.RelicDetailActivity
import com.example.chuculture.model.Relic

class RelicMainAdapter : ListAdapter<List<Relic>, RelicMainAdapter.ViewHolder>(RelicDiff()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object{
        class RelicDiff:DiffUtil.ItemCallback<List<Relic>>(){
            override fun areItemsTheSame(oldItem: List<Relic>, newItem: List<Relic>): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: List<Relic>, newItem: List<Relic>): Boolean {
                return oldItem==newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.relic_banner_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val desc1="${getItem(position)[0].abstract1}..."
        val desc2="${getItem(position)[1].abstract1}..."
        val desc3="${getItem(position)[2].abstract1}..."

        holder.itemView.findViewById<TextView>(R.id.tv_relic_desc_bn_0).text=desc1
        holder.itemView.findViewById<TextView>(R.id.tv_relic_desc_bn_1).text=desc2
        holder.itemView.findViewById<TextView>(R.id.tv_relic_desc_bn_2).text=desc3

        var name1=getItem(position)[0].name
        var name2=getItem(position)[1].name
        var name3=getItem(position)[2].name

        if (name1.length>8){
            name1=name1.substring(0,8)+".."
        }
        if (name2.length>8){
            name2=name2.substring(0,8)+".."
        }
        if (name3.length>8){
            name3=name3.substring(0,8)+".."
        }

        holder.itemView.findViewById<TextView>(R.id.tv_relic_name_bn_0).text=name1
        holder.itemView.findViewById<TextView>(R.id.tv_relic_name_bn_1).text=name2
        holder.itemView.findViewById<TextView>(R.id.tv_relic_name_bn_2).text=name3

        holder.itemView.findViewById<ImageView>(R.id.img_relic_bn_0).loadNormal(getItem(position)[0].image)
        holder.itemView.findViewById<ImageView>(R.id.img_relic_bn_1).loadNormal(getItem(position)[1].image)
        holder.itemView.findViewById<ImageView>(R.id.img_relic_bn_2).loadNormal(getItem(position)[2].image)
        val context=holder.itemView.context

        holder.itemView.findViewById<ConstraintLayout>(R.id.cl_relic_0).setOnClickListener {
            val intent=Intent(context,RelicDetailActivity::class.java)
            intent.putExtra(RELIC_ID,getItem(position)[0].id)
            context.startActivity(intent)
        }
        holder.itemView.findViewById<ConstraintLayout>(R.id.cl_relic_1).setOnClickListener {
            val intent=Intent(context,RelicDetailActivity::class.java)
            intent.putExtra(RELIC_ID,getItem(position)[1].id)
            context.startActivity(intent)
        }
        holder.itemView.findViewById<ConstraintLayout>(R.id.cl_relic_2).setOnClickListener {
            val intent=Intent(context,RelicDetailActivity::class.java)
            intent.putExtra(RELIC_ID,getItem(position)[2].id)
            context.startActivity(intent)
        }





    }
}