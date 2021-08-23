package com.example.chuculture.widget

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ScaleInTransformer : ViewPager2.PageTransformer {
    private val mMinScale = DEFAULT_MIN_SCALE
    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val pageHeight = page.height
        //动画锚点设置为View中心
        //The animation anchor point is set to the center of the View
        page.pivotX = pageWidth / 2.toFloat()
        page.pivotY = pageHeight / 2.toFloat()
        if (position < -1) {
            //屏幕左侧不可见时
            //When the left side of the screen is not visible
            page.scaleX = mMinScale
            page.scaleY = mMinScale
            page.pivotY = pageWidth / 2.toFloat()
        } else if (position <= 1) {
            if (position < 0) {
                //屏幕左侧
                //Left side of screen
                //(0,-1)
                val scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
                page.pivotX = pageWidth.toFloat()
            } else {
                //屏幕右侧
                //Right side of screen
                //(1,0)
                val scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
                page.pivotX = pageWidth * ((1 - position) * DEFAULT_CENTER)
            }
        } else {
            //屏幕右侧不可见
            //Not visible on the right side of the screen
            page.pivotX = 0f
            page.scaleY = mMinScale
            page.scaleY = mMinScale
        }
    }

    companion object {
        const val DEFAULT_MIN_SCALE = 0.85f
        const val DEFAULT_CENTER = 0.5f
    }
}