package com.example.chuculture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chuculture.DataIntentTrans.Companion.RELIC_QUERY_KEYWORD
import com.example.chuculture.DataIntentTrans.Companion.RELIC_START_TYPE
import com.example.chuculture.DataIntentTrans.Companion.RELIC_TYPE_MATERIAL
import com.example.chuculture.DataIntentTrans.Companion.RELIC_TYPE_YEAR
import com.example.chuculture.R
import com.example.chuculture.adapter.RelicAdapter
import com.example.chuculture.viewmodel.RelicTypeViewModel

class RelicTypeActivity : AppCompatActivity() {
    private var startType:Int?=null
    private var queryKey:String?=null

    private val relicAdapter=RelicAdapter()
    private val viewModel by viewModels<RelicTypeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relic_type)
        initData()
        initView()
    }

    private fun initView() {
        val ryRelic=findViewById<RecyclerView>(R.id.ry_relic)
        val tbRelicType=findViewById<Toolbar>(R.id.tb_relic_type)
        ryRelic.adapter=relicAdapter
        val linearLayoutManager=LinearLayoutManager(this)
        ryRelic.layoutManager=linearLayoutManager

        tbRelicType.setNavigationOnClickListener {
            this.finish()
        }

        viewModel.response.observe(this,{
            if (it.code==1&&it.data!=null){
                if (startType== RELIC_TYPE_YEAR){
                    tbRelicType.title= "年代:$queryKey"
                }
                if (startType== RELIC_TYPE_MATERIAL){
                    tbRelicType.title="材质:$queryKey"
                }
                relicAdapter.submitList(it.data)
            }
        })
    }

    private fun initData() {
        startType=intent.getIntExtra(RELIC_START_TYPE,-1)
        queryKey=intent.getStringExtra(RELIC_QUERY_KEYWORD)
        if (startType== RELIC_TYPE_YEAR&&queryKey!=null){
            viewModel.getRelicByYear(queryKey!!)
        }
        if (startType== RELIC_TYPE_MATERIAL&&queryKey!=null){
            viewModel.getRelicByMaterial(queryKey!!)
        }
    }
}