package com.example.chuculture.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import cn.jzvd.JZUtils
import cn.jzvd.Jzvd
import com.example.chuculture.R
import com.example.chuculture.adapter.FragmentAdapter
import com.example.chuculture.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val vp:ViewPager2=findViewById(R.id.vp)
        val tvTabImg:TextView=findViewById(R.id.tv_tab_image)
        val tvTabVideo:TextView=findViewById(R.id.tv_tab_video)
        val imgSearch:ImageView=findViewById(R.id.img_search)

        vp.adapter=FragmentAdapter(this)
        vp.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position==0){
                    val tvImgSize=10+5*(2-positionOffset)
                    val tvVideoSize=10+5*(1+positionOffset)
                    tvTabImg.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.grey))
                    tvTabVideo.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.grey))
                    tvTabImg.setTextSize(TypedValue.COMPLEX_UNIT_SP,tvImgSize)
                    tvTabVideo.setTextSize(TypedValue.COMPLEX_UNIT_SP,tvVideoSize)
                }
                if(position==0&&positionOffset==0f) tvTabImg.setTextColor(ContextCompat.getColor(this@MainActivity,
                    R.color.black
                ))
                if(position==1&&positionOffset==0f) tvTabVideo.setTextColor(ContextCompat.getColor(this@MainActivity,
                    R.color.black
                ))

                handleJzPlay(position)
            }
        })

        tvTabImg.setOnClickListener {
            vp.currentItem=0
        }
        tvTabVideo.setOnClickListener {
            vp.currentItem=1
        }

        imgSearch.setOnClickListener {
            val intent= Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleJzPlay(position:Int) {
        if (position==0){
            Jzvd.releaseAllVideos()
        }
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }

    override fun onDestroy() {
        super.onDestroy()
        Jzvd.releaseAllVideos()
    }

}