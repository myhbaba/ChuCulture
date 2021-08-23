package com.example.chuculture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import cc.shinichi.library.ImagePreview
import com.example.chuculture.DataIntentTrans.Companion.PAPER_ID
import com.example.chuculture.R
import com.example.chuculture.Utils.loadNormal
import com.example.chuculture.databinding.ActivityPaperBinding
import com.example.chuculture.network.NetWorkClient.BASE_IMAGE_URL
import com.example.chuculture.viewmodel.PaperViewModel

class PaperActivity : AppCompatActivity() {

    private val viewModel by viewModels<PaperViewModel>()
    private lateinit var binding:ActivityPaperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaperBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initData()
        initView()
    }



    private fun initData() {
        val id=intent.getIntExtra(PAPER_ID,-1)
        if (id>=0){
            viewModel.getPaperById(id)
        }
    }

    private fun initView() {
        viewModel.response.observe(this,{response->
            if (response.code==1&&response.data!=null){
                response.data.image?.let { binding.imgPaper.loadNormal(it) }
                binding.tvAbstract.text= response.data.abstract1
                binding.tvPaperName.text=response.data.name
                binding.tvKeyword.text=response.data.keyword
                binding.tvPaperJournal.text=response.data.journals
                binding.tvTime.text=response.data.time
                binding.tvPaperAuthor.text=response.data.author
                imgListener(response.data.image)
            }
        })


        binding.tbPaper.setNavigationOnClickListener {
            this.finish()
        }
    }

    private fun imgListener(url:String?){
        url?.let {
            binding.imgPaper.setOnClickListener {
                ImagePreview
                    .getInstance()
                    .setContext(this)
                    .setImage(BASE_IMAGE_URL+url)
                    .start()
            }
        }
    }
}