package com.example.chuculture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.chuculture.R
import com.example.chuculture.adapter.RelicAdapter
import com.example.chuculture.viewmodel.RelicActivityViewModel

class RelicActivity : AppCompatActivity() {


    private val viewModel by viewModels<RelicActivityViewModel>()
    private val relicAdapter=RelicAdapter()
    private var srlRelic:SwipeRefreshLayout?=null
    private var ryRelic:RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relic)
        initData()
        initView()
        initObserve()
    }




    private fun initData() {
        viewModel.getUpdateRelic()
    }

    private fun initView() {
        ryRelic=findViewById(R.id.ry_relic)
        srlRelic=findViewById(R.id.srl_relic)
        val tbRelic=findViewById<Toolbar>(R.id.tb_relic)

        tbRelic.setNavigationOnClickListener {
            this.finish()
        }

        val linearLayoutManager=LinearLayoutManager(this)
        ryRelic?.layoutManager=linearLayoutManager
        ryRelic?.adapter=relicAdapter
        srlRelic?.setOnRefreshListener {
            viewModel.getUpdateRelic()
        }
    }

    private fun initObserve(){
        viewModel.response.observe(this,{
            srlRelic?.isRefreshing=false
            if (it.code==1&&it.data!=null){
                relicAdapter.submitList(it.data)
            }
        })
    }
}