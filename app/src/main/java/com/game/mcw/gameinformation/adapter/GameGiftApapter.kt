package com.game.mcw.gameinformation.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.modle.Game
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.modle.News
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class GameGiftApapter(layid: Int) : BaseMVAdapter<GameGift, MVViewHolder>(layid) {
    private var options: RequestOptions
    private var optionsAll: RequestOptions

    init {
        val roundedCornersTransformation = RoundedCornersTransformation(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 5), 0, RoundedCornersTransformation.CornerType.TOP)
        options = RequestOptions().transforms(CenterCrop(), roundedCornersTransformation)
        optionsAll = RequestOptions().transforms(CenterCrop(), RoundedCornersTransformation(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 5), 0, RoundedCornersTransformation.CornerType.ALL))
    }

    override fun convert(helper: MVViewHolder, item: GameGift) {

        helper.getBinding().setVariable(BR.gameGift, item)
        helper.getBinding().executePendingBindings()
//        helper.addOnClickListener(R.id.btn_play)
//        Glide.with(mContext).load(item.image).apply(options).into(helper.getView(R.id.iv_game_img))
//        Glide.with(mContext).load(item.icon).apply(optionsAll).into(helper.getView(R.id.iv_game_icon))
    }


}