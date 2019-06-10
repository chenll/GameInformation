package com.game.mcw.gameinformation.utils

import android.content.Context
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.R
import com.qmuiteam.qmui.util.QMUIDisplayHelper


object GlideUtil {


    /**
     *加载圆形头像
     */
    fun loadCircleHeadPic(url: String, view: ImageView) {
        loadCirclePic(view.context, url, view, R.mipmap.ic_launcher, R.mipmap.ic_launcher)
    }

    /**
     *加载圆图
     */
    private fun loadCirclePic(
            context: Context,
            url: String,
            view: ImageView, @DrawableRes place: Int, @DrawableRes error: Int
    ) {
        val requestOptions = RequestOptions.bitmapTransform(CircleCrop())
        val transformsPlace = Glide.with(view.context).load(place).apply(requestOptions)
        val transformsError = Glide.with(view.context).load(error).apply(requestOptions)
        Glide.with(context)
                .load(url)
                .placeholder(place)
                .thumbnail(transformsPlace)
                .error(error)
                .thumbnail(transformsError)
                .apply(requestOptions)
                .into(view)
    }

    /**
     *加载圆形游戏图标
     */
    fun loadBorderRadiusGameIcon(url: String, view: ImageView) {
        val roundedCorners = RoundedCorners(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 6))
//        val requestOptions = RequestOptions.bitmapTransform(MultiTransformation<Bitmap>(MultiTransformation(roundedCorners)))
        val requestOptions = RequestOptions.bitmapTransform(roundedCorners)
        val transforms = Glide.with(view.context).load(R.mipmap.ic_launcher).apply(requestOptions)
        Glide.with(view.context).load(url)
                .apply(requestOptions)
                .placeholder(R.mipmap.ic_launcher)
                .thumbnail(transforms)
                .error(R.mipmap.ic_launcher)
                .into(view)
    }


}