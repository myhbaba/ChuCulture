package com.example.chuculture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chuculture.R
import com.example.chuculture.adapter.MuseumListAdapter
import com.example.chuculture.viewmodel.MuseumViewModel

class MuseumActivity : AppCompatActivity() {

    private val viewModel by viewModels<MuseumViewModel>()
    private val museumAdapter = MuseumListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum)
        initData()
        initView()
    }



    private fun initData() {
        viewModel.getMuseumList()
    }

    private fun initView() {
        val tbMuseum=findViewById<Toolbar>(R.id.tb_museum)

        tbMuseum.setNavigationOnClickListener {
            this.finish()
        }


        val ryMuseum=findViewById<RecyclerView>(R.id.ry_museum)
        val linearLayoutManager=LinearLayoutManager(this)
        ryMuseum.layoutManager=linearLayoutManager
        ryMuseum.adapter=museumAdapter


        viewModel.response.observe(this,{
            if (it.code==1&&it.data!=null){
                museumAdapter.submitList(it.data)
            }
        })
    }


}