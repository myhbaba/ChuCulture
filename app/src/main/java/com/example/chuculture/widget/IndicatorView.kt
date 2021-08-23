package com.example.chuculture.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.chuculture.R

class IndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val blackPaint=Paint().apply {
        isAntiAlias=true
        color=ContextCompat.getColor(context,R.color.black)
        strokeWidth=1f
        style=Paint.Style.FILL
    }

    private val whitePaint=Paint().apply {
        isAntiAlias=true
        color=ContextCompat.getColor(context,R.color.white)
        strokeWidth=1f
        style=Paint.Style.FILL
    }


    private var viewWidth:Int?=null
    private var viewHeight:Int?=null
    private var selectPosition=0


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth=width
        viewHeight=height
    }
    private fun drawPoint(canvas: Canvas,position: Int){
        val pointX=viewWidth!!/5f
        val pointY=viewHeight!!/2f
        val radius=10f
        when(position){
            0->{
                canvas.drawCircle(pointX,pointY,radius,blackPaint)
                canvas.drawCircle(pointX*2,pointY,radius,whitePaint)
                canvas.drawCircle(pointX*3,pointY,radius,whitePaint)
                canvas.drawCircle(pointX*4,pointY,radius,whitePaint)
            }
            1->{
                canvas.drawCircle(pointX,pointY,radius,whitePaint)
                canvas.drawCircle(pointX*2,pointY,radius,blackPaint)
                canvas.drawCircle(pointX*3,pointY,radius,whitePaint)
                canvas.drawCircle(pointX*4,pointY,radius,whitePaint)

            }
            2->{
                canvas.drawCircle(pointX,pointY,radius,whitePaint)
                canvas.drawCircle(pointX*2,pointY,radius,whitePaint)
                canvas.drawCircle(pointX*3,pointY,radius,blackPaint)
                canvas.drawCircle(pointX*4,pointY,radius,whitePaint)

            }
            3->{
                canvas.drawCircle(pointX,pointY,radius,whitePaint)
                canvas.drawCircle(pointX*2,pointY,radius,whitePaint)
                canvas.drawCircle(pointX*3,pointY,radius,whitePaint)
                canvas.drawCircle(pointX*4,pointY,radius,blackPaint)
            }

        }


    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawPoint(it,selectPosition)
        }
    }

    fun setSelected(position:Int){
        selectPosition=position
        invalidate()
    }
}