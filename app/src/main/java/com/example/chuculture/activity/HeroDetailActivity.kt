package com.example.chuculture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.example.chuculture.DataIntentTrans.Companion.HERO_ID
import com.example.chuculture.R
import com.example.chuculture.Utils.loadLocalBanner
import com.example.chuculture.Utils.loadNormal
import com.example.chuculture.viewmodel.HeroDetailViewModel

class HeroDetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<HeroDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)
        initData()
        initView()
    }

    private fun initData() {
        val id=intent.getIntExtra(HERO_ID,-1)
        if (id>=0){
            viewModel.getHeroById(id)
        }
    }

    private fun initView() {
        val tvHeroName=findViewById<TextView>(R.id.tv_hero_name)
        val tvHeroAbstract=findViewById<TextView>(R.id.tv_hero_abstract)
        val tvHeroLyric=findViewById<TextView>(R.id.tv_hero_lyric)
        val imgHero=findViewById<ImageView>(R.id.img_hero)
        val tbHeroDetail=findViewById<Toolbar>(R.id.tb_hero_detail)

        tbHeroDetail.setNavigationOnClickListener {
            this.finish()
        }

        viewModel.response.observe(this,{
            if (it.code==1&&it.data!=null){
                tvHeroName.text=it.data.name
                tvHeroLyric.text=it.data.lyric
                tvHeroAbstract.text=it.data.abstract1
                imgHero.loadNormal(it.data.image)
            }
        })
    }

}