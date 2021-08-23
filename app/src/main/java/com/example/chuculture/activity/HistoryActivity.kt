package com.example.chuculture.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.chuculture.R
import com.example.chuculture.adapter.HistoryPageAdapter
import com.example.chuculture.widget.IndicatorView


class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        initStatusBar()
        initView()
    }

    private fun initStatusBar() {
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    private fun initView() {
        val vpHistory = findViewById<ViewPager2>(R.id.vp_history)
        val indHistory=findViewById<IndicatorView>(R.id.ind_history)
        val btnStart=findViewById<Button>(R.id.btn_start)
        val imgIndLast=findViewById<ImageView>(R.id.img_ind_last)
        val imgIndNext=findViewById<ImageView>(R.id.img_ind_next)
        vpHistory.adapter = HistoryPageAdapter(this)
        vpHistory.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                indHistory.setSelected(position)
                btnStart.isVisible = position==3
                imgIndLast.isVisible= position!=0
                imgIndNext.isVisible=position!=3
            }
        })

        imgIndNext.setOnClickListener {
            var i=vpHistory.currentItem
            vpHistory.currentItem = ++i
        }
        imgIndLast.setOnClickListener {
            var i=vpHistory.currentItem
            vpHistory.currentItem = --i
        }

        btnStart.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}