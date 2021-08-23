package com.example.chuculture.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.shinichi.library.ImagePreview
import com.example.chuculture.DataIntentTrans.Companion.PAPER_ID
import com.example.chuculture.DataIntentTrans.Companion.RELIC_ID
import com.example.chuculture.DataIntentTrans.Companion.RELIC_QUERY_KEYWORD
import com.example.chuculture.DataIntentTrans.Companion.RELIC_START_TYPE
import com.example.chuculture.DataIntentTrans.Companion.RELIC_TYPE_MATERIAL
import com.example.chuculture.DataIntentTrans.Companion.RELIC_TYPE_YEAR
import com.example.chuculture.R
import com.example.chuculture.Utils.loadNormal
import com.example.chuculture.adapter.RelicImageAdapter
import com.example.chuculture.network.NetWorkClient.BASE_IMAGE_URL
import com.example.chuculture.viewmodel.RelicDetailViewModel
import org.w3c.dom.Text
import kotlin.math.abs

class RelicDetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<RelicDetailViewModel>()
    private var id:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relic_detail)
        initData()
        initView()
    }

    private fun initData() {
        id=intent.getIntExtra(RELIC_ID,0)
        viewModel.getRelicById(id!!)
    }

    private fun initView() {
        val imgRelic=findViewById<ImageView>(R.id.img_relic)
        val tvRelicName=findViewById<TextView>(R.id.tv_relic_name)
        val tvRelicAbstract=findViewById<TextView>(R.id.tv_relic_abstract)
        val tvRelicYear=findViewById<TextView>(R.id.tv_relic_year)
        val tvRelicMaterial=findViewById<TextView>(R.id.tv_relic_material)
        val llRelicPaper0=findViewById<LinearLayout>(R.id.ll_relic_paper_1)
        val llRelicPaper1=findViewById<LinearLayout>(R.id.ll_relic_paper_2)
        val tvRelicDescMore=findViewById<TextView>(R.id.tv_relic_desc_more)

        llRelicPaper0.isVisible=false
        llRelicPaper1.isVisible=false
        val tvRelicPaperAbstract0=findViewById<TextView>(R.id.tv_relic_paper_abstract_0)
        val tvRelicPaperAbstract1=findViewById<TextView>(R.id.tv_relic_paper_abstract_1)
        val tvRelicPaperName0=findViewById<TextView>(R.id.tv_relic_paper_name_0)
        val tvRelicPaperName1=findViewById<TextView>(R.id.tv_relic_paper_name_1)
        val tbRelicDetail=findViewById<Toolbar>(R.id.tb_relic_detail)


        viewModel.response.observe(this,{ response ->
            if (response.code==1&&response.data!=null){

                val name=if (response.data.name.length>9){
                    response.data.name.substring(0,9)+".."
                }else{
                    response.data.name
                }
                tvRelicName.text=name





                tvRelicYear.text=response.data.year
                tvRelicMaterial.text=response.data.material
                imgRelic.loadNormal(response.data.image)
                val desc=if (response.data.abstract1.length<12){
                    response.data.abstract1+"..."
                }else{
                    response.data.abstract1.substring(0,12)+"..."
                }
                tvRelicAbstract.text=desc

                if (response.data.paperList!=null&&response.data.paperList.size==1){
                    tvRelicPaperName0.text=response.data.paperList[0].name
                    var abstract=response.data.paperList[0].abstract1
                    if (abstract.length>20){
                        abstract=abstract.substring(0,20)+"..."
                    }
                    tvRelicPaperAbstract0.text= abstract
                    paperListener(llRelicPaper0,response.data.paperList[0].id)
                    llRelicPaper0.isVisible=true

                }
                if (response.data.paperList!=null&&response.data.paperList.size>1){
                    tvRelicPaperName0.text=response.data.paperList[0].name
                    tvRelicPaperName1.text=response.data.paperList[1].name
                    var abstract1=response.data.paperList[0].abstract1
                    if (abstract1.length>20){
                        abstract1=abstract1.substring(0,20)+"..."
                    }
                    tvRelicPaperAbstract0.text= abstract1
                    var abstract2=response.data.paperList[1].abstract1
                    if (abstract2.length>20){
                        abstract2=abstract2.substring(0,20)+"..."
                    }
                    tvRelicPaperAbstract1.text= abstract2
                    paperListener(llRelicPaper0,response.data.paperList[0].id)
                    paperListener(llRelicPaper1,response.data.paperList[1].id)
                    llRelicPaper0.isVisible=true
                    llRelicPaper1.isVisible=true
                }

                response.data.year?.let { data -> setYearListener(tvRelicYear, data) }
                response.data.material?.let { data->setMaterialListener(tvRelicMaterial,data) }



                imgRelic.setOnClickListener {
                    setPreview(response.data.image)
                }

                tvRelicDescMore.setOnClickListener{
                    val intent= Intent(this@RelicDetailActivity,RelicAbstractDetailActivity::class.java)
                    intent.putExtra(RELIC_ID,id)
                    startActivity(intent)
                }

                tbRelicDetail.setNavigationOnClickListener {
                    this.finish()
                }
            }
        })
    }

    private fun paperListener(view:View,paperId:Int){
        view.setOnClickListener {
            val intent=Intent(this,PaperActivity::class.java)
            intent.putExtra(PAPER_ID,paperId)
            startActivity(intent)
        }
    }




    private fun setYearListener(view:View,year:String){
        view.setOnClickListener {
            val intent=Intent(this,RelicTypeActivity::class.java)
            intent.putExtra(RELIC_START_TYPE, RELIC_TYPE_YEAR)
            intent.putExtra(RELIC_QUERY_KEYWORD,year)
            startActivity(intent)
        }
    }

    private fun setMaterialListener(view:View,material:String){
        view.setOnClickListener {
            val intent=Intent(this,RelicTypeActivity::class.java)
            intent.putExtra(RELIC_START_TYPE, RELIC_TYPE_MATERIAL)
            intent.putExtra(RELIC_QUERY_KEYWORD,material)
            startActivity(intent)
        }
    }

    private fun setPreview(url:String){
        ImagePreview.getInstance()
            .setContext(this)
            .setIndex(0)
            .setImage(BASE_IMAGE_URL+url)
            .start()
    }
}