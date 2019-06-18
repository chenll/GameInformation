package com.game.mcw.gameinformation.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.zhihu.matisse.engine.ImageEngine


class GlideV4ImageEngine : ImageEngine {
    override fun loadGifImage(context: Context, resizeX: Int, resizeY: Int, imageView: ImageView, uri: Uri?) {
        val options = RequestOptions()
                .centerCrop()
                .override(resizeX, resizeY)
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(options)
                .into(imageView)

    }

    override fun supportAnimatedGif(): Boolean {
        return true
    }

    override fun loadGifThumbnail(context: Context, resize: Int, placeholder: Drawable, imageView: ImageView, uri: Uri?) {
        val options = RequestOptions()
                .centerCrop()
                .placeholder(placeholder)//这里可自己添加占位图
                .override(resize, resize)
        Glide.with(context)
                .asGif()  // some .jpeg files are actually gif
                .load(uri)
                .apply(options)
                .into(imageView)

    }

    override fun loadImage(context: Context, resizeX: Int, resizeY: Int, imageView: ImageView, uri: Uri) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.priority(Priority.HIGH)
        requestOptions = requestOptions.override(resizeX, resizeY)
        requestOptions = requestOptions.centerCrop()
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(requestOptions)
                .into(imageView)
    }

    override fun loadThumbnail(context: Context?, resize: Int, placeholder: Drawable?, imageView: ImageView?, uri: Uri?) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.placeholder(placeholder)
        requestOptions = requestOptions.override(resize, resize)
        requestOptions = requestOptions.centerCrop()
        Glide.with(context!!)
                .asBitmap()
                .load(uri)
                .apply(requestOptions)
                .into(imageView!!)
    }
}