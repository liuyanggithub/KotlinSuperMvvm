package com.ly.kotlinsupermvvm.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
 * 保留长宽比的imageview
 * @author 刘阳
 * @version 1.0
 *
 *
 * Create by 2016/2/29 17:43
 * @see https://github.com/liuyanggithub/SuperMvp
 */
class RatioImageView : ImageView {

    private var originalWidth: Int = 0
    private var originalHeight: Int = 0


    constructor(context: Context) : super(context) {}


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    fun setOriginalSize(originalWidth: Int, originalHeight: Int) {
        this.originalWidth = originalWidth
        this.originalHeight = originalHeight
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (originalWidth > 0 && originalHeight > 0) {
            val ratio = originalWidth.toFloat() / originalHeight.toFloat()

            val width = View.MeasureSpec.getSize(widthMeasureSpec)
            var height = View.MeasureSpec.getSize(heightMeasureSpec)

            // TODO: 现在只支持固定宽度
            if (width > 0) {
                height = (width.toFloat() / ratio).toInt()
            }

            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
