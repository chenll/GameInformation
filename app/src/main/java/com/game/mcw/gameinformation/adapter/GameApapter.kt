package com.game.mcw.gameinformation.adapter

import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.modle.Game
import com.game.mcw.gameinformation.utils.GlideUtil
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class GameApapter(layid: Int) : BaseMVAdapter<Game, MVViewHolder>(layid) {
    private var options: RequestOptions

    init {
        val roundedCornersTransformation = RoundedCornersTransformation(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 5), 0, RoundedCornersTransformation.CornerType.TOP)
        val multiTransformation = MultiTransformation<Bitmap>(CenterCrop(), roundedCornersTransformation)
        options = bitmapTransform(multiTransformation)
    }

    override fun convert(helper: MVViewHolder, item: Game) {
        if (!item.plays.contains("人在玩")) {
            item.plays = "${item.plays}人在玩"
        }
        helper.getBinding().setVariable(BR.game, item)
        helper.getBinding().executePendingBindings()
        helper.addOnClickListener(R.id.btn_play)
        GlideUtil.loadBorderRadiusGameIcon(item.icon, helper.getView(R.id.iv_game_icon))
        Glide.with(mContext).load(item.image).apply(options).into(helper.getView(R.id.iv_game_img))
    }


}