package com.game.mcw.gameinformation.binding

import android.databinding.BindingAdapter
import android.support.annotation.ColorInt
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.utils.GlideUtil
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration

object BindingAdapters {

    /**
     * 加载圆角图片
     */
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(uri: String) {
//        GlideUtil.loadCircleHeadPic(uri, this)
        GlideUtil.loadBorderRadiusGameIcon(uri, this)
    }


    /**
     *RecyclerView设置LayoutManager
     */
    @BindingAdapter(value = ["rvOrientation", "rvColumns", "rvDecorationSize", "rvDecorationColor"], requireAll = false)
    @JvmStatic
    fun initRecyclerView(recyclerView: RecyclerView, orientation: Int?, columns: Int?, decorationSize: Int?, @ColorInt decorationColor: Int?) {
        val lineSizeException = decorationSize
                ?: recyclerView.context.resources.getDimensionPixelSize(R.dimen.common_decoration_size)
        if (columns == null) {
            val recyclerViewOrientation = orientation ?: LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, recyclerViewOrientation, false)
            decorationColor?.let {
                if (recyclerViewOrientation == LinearLayoutManager.VERTICAL) {
                    recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(recyclerView.context).size(lineSizeException).color(decorationColor).build())
                } else {
                    recyclerView.addItemDecoration(VerticalDividerItemDecoration.Builder(recyclerView.context).size(lineSizeException).color(decorationColor).build())
                }
            }


        } else {
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, columns)
            decorationColor?.let {
                recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(recyclerView.context).size(lineSizeException).color(decorationColor).build())
                recyclerView.addItemDecoration(VerticalDividerItemDecoration.Builder(recyclerView.context).size(lineSizeException).color(decorationColor).build())
            }
        }

    }
}