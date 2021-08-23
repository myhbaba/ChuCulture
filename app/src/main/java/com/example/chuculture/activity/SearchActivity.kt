package com.example.chuculture.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chuculture.R
import com.example.chuculture.adapter.RelicImageAdapter
import com.example.chuculture.viewmodel.SearchViewModel
import com.google.android.material.chip.Chip

class SearchActivity : AppCompatActivity() {
    private val relicImageAdapter = RelicImageAdapter()
    private val viewModel by viewModels<SearchViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    private fun initView() {
        val sv = findViewById<SearchView>(R.id.sv)
        val rySearchRelic = findViewById<RecyclerView>(R.id.ry_search_relic)
        val llRelic = findViewById<LinearLayout>(R.id.ll_relic)
        val llHero = findViewById<LinearLayout>(R.id.ll_hero)
        val chip1 = findViewById<Chip>(R.id.chip_1)
        val chip2 = findViewById<Chip>(R.id.chip_2)

        llRelic.visibility = View.GONE
        llHero.visibility = View.GONE
        rySearchRelic.adapter = relicImageAdapter
        val gridLayoutManager = GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false)
        rySearchRelic.layoutManager = gridLayoutManager

        sv.setOnClickListener {
            sv.onActionViewExpanded()
        }

        chip1.setOnClickListener {
            sv.setQuery("鸟兽合体形器", true)
        }
        chip2.setOnClickListener {
            sv.setQuery("兽面纹爵", true)
        }

        //搜索框的内容提交的监听
        //Monitoring of content submission in search box
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            //点击提交后
            //After clicking submit
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.getSearchRelic(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        viewModel.response.observe(this, {
            if (it.code == 1 && it.data != null && it.data.relicList != null) {
                llRelic.visibility = View.VISIBLE
                relicImageAdapter.submitList(it.data.relicList)
            }
        })
    }
}