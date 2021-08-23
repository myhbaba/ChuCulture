package com.example.chuculture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.chuculture.DataIntentTrans.Companion.MUSEUM_ID
import com.example.chuculture.R
import com.example.chuculture.Utils.loadLocalBanner
import com.example.chuculture.viewmodel.MuseumDetailViewModel
import org.w3c.dom.Text

class MuseumDetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<MuseumDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_detail)
        initData()
        initView()
    }



    private fun initData() {
        val id=intent.getIntExtra(MUSEUM_ID,0)
        viewModel.getMuseumById(id)
    }

    private fun initView() {
        val tbMuseum=findViewById<Toolbar>(R.id.tb_museum_detail)
        val imgMuseum=findViewById<ImageView>(R.id.img_museum)
        val tvIntro1=findViewById<TextView>(R.id.tv_museum_intro1)
        val tvIntro2=findViewById<TextView>(R.id.tv_museum_intro2)
        val tvIntro3=findViewById<TextView>(R.id.tv_museum_intro3)

        val tvMuseum1=findViewById<TextView>(R.id.tv_museum_1)
        val tvMuseum2=findViewById<TextView>(R.id.tv_museum_2)
        val tvMuseum3=findViewById<TextView>(R.id.tv_museum_3)

        tvMuseum2.isVisible=false
        tvMuseum3.isVisible=false

        tbMuseum.setNavigationOnClickListener {
            this.finish()
        }

        viewModel.response.observe(this, Observer {
            if (it.code==1&&it.data!=null){
                imgMuseum.loadLocalBanner(it.data.image)
                tvIntro1.text=it.data.introduction[0]
                tbMuseum.title=it.data.name
                if (it.data.introduction.size==2){
                    tvMuseum2.isVisible=true
                    tvIntro2.text=it.data.introduction[1]
                }
                if (it.data.introduction.size==3){
                    tvMuseum2.isVisible=true
                    tvIntro2.text=it.data.introduction[1]
                    tvMuseum3.isVisible=true
                    tvIntro3.text=it.data.introduction[2]
                }
            }
        })
    }
}