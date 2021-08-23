package com.example.chuculture

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.chuculture.network.NetWorkClient
import com.google.gson.Gson
import jp.wasabeef.glide.transformations.BlurTransformation

object Utils {

    fun ViewPager2.setCurrentItemLowSpeed(
        item: Int,
        duration: Long,
        interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
        pagePxWidth: Int = width // 使用viewpager2.getWidth()获取
    ) {
        val pxToDrag: Int = pagePxWidth * (item - currentItem)
        val animator = ValueAnimator.ofInt(0, pxToDrag)
        var previousValue = 0
        animator.addUpdateListener { valueAnimator ->
            val currentValue = valueAnimator.animatedValue as Int
            val currentPxToDrag = (currentValue - previousValue).toFloat()
            fakeDragBy(-currentPxToDrag)
            previousValue = currentValue
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) { beginFakeDrag() }
            override fun onAnimationEnd(animation: Animator?) { endFakeDrag() }
            override fun onAnimationCancel(animation: Animator?) { }
            override fun onAnimationRepeat(animation: Animator?) { }
        })
        animator.interpolator = interpolator
        animator.duration = duration
        animator.start()
    }


    fun ImageView.loadBanner(url:String){
        Glide.with(this.context)
            .load(url)
            .addListener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let {
                        val params: ViewGroup.LayoutParams = this@loadBanner.layoutParams
                        val imgWidth=resource.intrinsicWidth
                        val imgHeight=resource.intrinsicHeight
                        val viewWidth=this@loadBanner.width
                        val scale: Double = imgWidth/(viewWidth*1.0)
                        val viewHeight=(imgHeight/scale).toInt()
                        params.width=viewWidth
                        params.height=viewHeight
                        this@loadBanner.layoutParams=params
                    }
                    return false
                }
            })
            .into(this)
    }


    fun ImageView.loadBanner(url:Int){
        Glide.with(this.context)
            .load(url)
            .addListener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let {
                        val params: ViewGroup.LayoutParams = this@loadBanner.layoutParams
                        val imgWidth=resource.intrinsicWidth
                        val imgHeight=resource.intrinsicHeight
                        val viewWidth=this@loadBanner.width
                        val scale: Double = imgWidth/(viewWidth*1.0)
                        val viewHeight=(imgHeight/scale).toInt()
                        params.width=viewWidth
                        params.height=viewHeight
                        this@loadBanner.layoutParams=params
                    }
                    return false
                }
            })
            .into(this)
    }

    fun ImageView.loadNormal(url:String){
        Glide.with(this.context).load("${NetWorkClient.BASE_IMAGE_URL}$url").into(this)
    }
    fun ImageView.loadNormal(url:Int){
        Glide.with(this.context).load(url).into(this)
    }

    fun getGson()= Gson()


    fun ImageView.loadBlur(image:String){
        val blur = MultiTransformation<Bitmap>(
            BlurTransformation(25)
        )
        Glide.with(this.context)
            .load("${NetWorkClient.BASE_IMAGE_URL}$image")
            .apply(RequestOptions.bitmapTransform(blur))
            .into(this)
    }

    fun ImageView.loadLocalBanner(image:String){
        loadBanner("${NetWorkClient.BASE_IMAGE_URL}$image")
    }

    fun ImageView.load(url: String){
        Glide.with(this.context).load(url).into(this)
    }

}