package com.game.mcw.gameinformation.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.game.mcw.gameinformation.modle.IndexCommon
import com.youth.banner.loader.ImageLoader

class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context).load((path as IndexCommon).image).into(imageView)
    }
}