package com.example.chuculture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.example.chuculture.DataIntentTrans.Companion.RELIC_ID
import com.example.chuculture.R
import com.example.chuculture.viewmodel.RelicDetailViewModel
import org.w3c.dom.Text

class RelicAbstractDetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<RelicDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relic_abstract_detail)
        initData()
        initView()
    }



    private fun initData() {
        val id=intent.getIntExtra(RELIC_ID,0)
        viewModel.getRelicById(id)
    }

    private fun initView() {
        val tvAbstract0=findViewById<TextView>(R.id.tv_abstract_0)
        val tvAbstract1=findViewById<TextView>(R.id.tv_abstract_1)
        val tvAbstract2=findViewById<TextView>(R.id.tv_abstract_2)
        val tbDetail=findViewById<Toolbar>(R.id.tb_detail)
        val tvAbstractName=findViewById<TextView>(R.id.tv_abstract_name)

        tbDetail.setNavigationOnClickListener {
            this.finish()
        }

        viewModel.response.observe(this,{
            if (it.code ==1&&it.data!=null){
                tvAbstract0.text=it.data.abstract1
                tvAbstract1.text=it.data.abstract2
                tvAbstract2.text=it.data.abstract3
                tvAbstractName.text=it.data.name
            }
        })
    }
}