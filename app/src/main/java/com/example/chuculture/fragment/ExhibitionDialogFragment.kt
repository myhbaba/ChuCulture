package com.example.chuculture.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.chuculture.R
import com.example.chuculture.Utils.loadLocalBanner
import com.example.chuculture.model.ThemeCollection

class ExhibitionDialogFragment: DialogFragment() {

    internal lateinit var listener: NoticeDialogListener
    private var themeCollection:ThemeCollection?=null

    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view=inflater.inflate(R.layout.dialog_exhibition,null)
            initView(view)
            builder.setView(view)
                .setPositiveButton(R.string.enter_exhibition
                ) { _, _ ->
                    listener.onDialogPositiveClick(this)
                }
                .setNegativeButton(R.string.cancel
                ) { _, _ ->
                    listener.onDialogNegativeClick(this)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }




    private fun initView(view:View) {
        val tvExhibitionName=view.findViewById<TextView>(R.id.tv_exhibition_name)
        val tvExhibitionIntro=view.findViewById<TextView>(R.id.tv_exhibition_intro)
        val imgExhibitionBlur=view.findViewById<ImageView>(R.id.img_exhibition_blur)

        tvExhibitionName.text=themeCollection?.name
        tvExhibitionIntro.text=themeCollection?.introduction
        themeCollection?.let { imgExhibitionBlur.loadLocalBanner(it.image) }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    fun setThemeCollection(themeCollection: ThemeCollection){
        this.themeCollection=themeCollection
    }
}