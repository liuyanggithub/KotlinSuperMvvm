package com.ly.kotlinsupermvvm.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ly.kotlinsupermvvm.R
import com.ly.kotlinsupermvvm.entity.OpenApiPicture



/**
 * <Pre>
 *     图片适配器
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/18 17:21
 */
class PictureAdapter :
    BaseQuickAdapter<OpenApiPicture, BaseViewHolder>(com.ly.kotlinsupermvvm.R.layout.item_picture_grid){
    override fun convert(helper: BaseViewHolder?, item: OpenApiPicture?) {
        helper?.setText(R.id.tv_title, item?.time)
        val imageView = helper?.getView<ImageView>(R.id.iv_picture)
        Glide.with(mContext).load(item?.img).into(imageView!!)
    }
}