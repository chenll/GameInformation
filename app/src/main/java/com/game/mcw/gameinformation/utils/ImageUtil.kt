package com.game.mcw.gameinformation.utils

import android.databinding.BindingAdapter
import android.widget.ImageView

object ImageUtil {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(uri: String) {
//        GlideUtil.loadCircleHeadPic(uri, this)
        GlideUtil.loadBorderRadiusGameIcon(uri, this)
    }
}