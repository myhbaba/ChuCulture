package com.example.chuculture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.shinichi.library.ImagePreview
import com.example.chuculture.OnItemClickListener
import com.example.chuculture.R
import com.example.chuculture.adapter.ExhibitionAdapter
import com.example.chuculture.fragment.ExhibitionDialogFragment
import com.example.chuculture.model.ThemeCollection
import com.example.chuculture.network.NetWorkClient.BASE_IMAGE_URL
import com.example.chuculture.viewmodel.ExhibitionActivityViewModel

class ExhibitionActivity : AppCompatActivity(),ExhibitionDialogFragment.NoticeDialogListener {

    private val viewModel by viewModels<ExhibitionActivityViewModel>()
    private val exhibitionDialogFragment=ExhibitionDialogFragment()
    private var themeCollectionId=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exhibition)
        initData()
        initView()
    }

    private fun initData() {
        viewModel.getAllThemeCollection()
    }

    private fun initView() {
        val ryExhibition=findViewById<RecyclerView>(R.id.ry_exhibition)
        val tbExhibition=findViewById<Toolbar>(R.id.tb_exhibition)


        tbExhibition.setNavigationOnClickListener {
            this.finish()
        }


        val linearLayoutManager=LinearLayoutManager(this)

        ryExhibition.layoutManager=linearLayoutManager


        val exhibitionAdapter=ExhibitionAdapter(object :OnItemClickListener{
            override fun click(themeCollection: ThemeCollection) {
                exhibitionDialogFragment.setThemeCollection(themeCollection)
                exhibitionDialogFragment.show(supportFragmentManager,"ExhibitionFragment")
                themeCollectionId=themeCollection.id

            }
        })


        ryExhibition.adapter=exhibitionAdapter


        viewModel.response.observe(this,{
            if (it.code==1){
                exhibitionAdapter.submitList(it.data)
            }
        })
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        viewModel.getThemeCollectionById(themeCollectionId)
        viewModel.respDetailById.observe(this,{
            if (it.code==1&&it.data!=null&&it.data.imageList!=null){
                val imageList= mutableListOf<String>()
                for (item in it.data.imageList){
                    imageList.add("$BASE_IMAGE_URL${item.image}")
                }
                startExhibition(imageList)
            }
        })

    }



    override fun onDialogNegativeClick(dialog: DialogFragment) {
        dialog.dismiss()
    }


    private fun startExhibition(imageList:List<String>) {
        ImagePreview
            .getInstance()
            .setContext(this)
            .setIndex(0)
            .setImageList(imageList)
            .start()
    }
}