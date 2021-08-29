package com.example.chuculture.activity

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.example.chuculture.LyricDataStore
import com.example.chuculture.R

class LaunchActivity : AppCompatActivity() {
    private var content: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        initView()
    }

    private fun initView() {
        val tvLyric = findViewById<TextView>(R.id.tv_lyric)
        val lyric = LyricDataStore().generateLyric()
        lyric?.let {
            content = lyric.random()
            tvLyric.text = content
        }

        counterTimer.start()
    }

    private val counterTimer=object :CountDownTimer(2000,100){
        override fun onTick(p0: Long) {

        }

        override fun onFinish() {
            val intent=Intent(this@LaunchActivity,HistoryActivity::class.java)
            startActivity(intent)
            this@LaunchActivity.finish()
        }
    }
}