package com.example.chuculture.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.chuculture.R
import com.example.chuculture.Utils.loadBanner
import com.example.chuculture.model.History

class HistoryFragment : Fragment() {
    private var history:History?=null


    companion object {
        fun newInstance(history:History) = HistoryFragment().apply {
            arguments=Bundle().apply {
                putParcelable("history",history)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView(view)
    }

    private fun initData() {
        history=arguments?.getParcelable("history")
    }

    private fun initView(view: View) {
        val imgHistoryBack=view.findViewById<ImageView>(R.id.img_history_back)
        val imgHistory=view.findViewById<ImageView>(R.id.img_history)
        val tvHistoryTitle=view.findViewById<TextView>(R.id.tv_history_title)
        val tvHistoryDesc=view.findViewById<TextView>(R.id.tv_history_desc)

        history?.let {
            imgHistoryBack.setImageResource(it.background)
            imgHistory.loadBanner(it.image)
            tvHistoryDesc.text=it.desc
            tvHistoryTitle.text=it.title
        }
    }
}