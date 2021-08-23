package com.example.chuculture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chuculture.R
import com.example.chuculture.adapter.HeroAdapter
import com.example.chuculture.viewmodel.HeroViewModel

class HeroActivity : AppCompatActivity() {

    private val viewModel by viewModels<HeroViewModel>()
    private val heroAdapter=HeroAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)
        initData()
        initView()
    }

    private fun initData() {
        viewModel.getHero()
    }
    private fun initView(){
        val ryHero=findViewById<RecyclerView>(R.id.ry_hero)
        val tbHero=findViewById<Toolbar>(R.id.tb_hero)

        tbHero.setNavigationOnClickListener {
            this.finish()
        }

        val linearLayoutManager=LinearLayoutManager(this)
        ryHero.layoutManager=linearLayoutManager
        ryHero.adapter=heroAdapter

        viewModel.response.observe(this,{
            if (it.code==1&&it.data!=null){
                heroAdapter.submitList(it.data)
            }
        })
    }
}