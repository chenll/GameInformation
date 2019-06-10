package com.game.mcw.gameinformation.adapter

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemMyCardBinding
import com.game.mcw.gameinformation.modle.Card
import com.qmuiteam.qmui.util.QMUIDisplayHelper


class MyCardApapter(layid: Int) : BaseMVAdapter<Card, MVViewHolder>(layid) {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun convert(helper: MVViewHolder, item: Card) {
        val binding = helper.getBinding() as ItemMyCardBinding
        binding.setVariable(BR.item, item)
        val bgDrawable = GradientDrawable(GradientDrawable.Orientation.TR_BL, item.bgColor)
        val rad = QMUIDisplayHelper.dp2px(mContext, 4).toFloat()
        bgDrawable.cornerRadii = floatArrayOf(rad, rad, rad, rad, rad, rad, rad, rad)
        binding.clCardbg.background = bgDrawable
        binding.executePendingBindings()

    }

    override fun createBaseViewHolder(view: View?): MVViewHolder {
        val holder = super.createBaseViewHolder(view)
        holder.itemView.layoutParams.width = QMUIDisplayHelper.getScreenWidth(mContext) / 4
        holder.itemView.layoutParams.height = QMUIDisplayHelper.getScreenWidth(mContext) / 4
        return holder
    }

}