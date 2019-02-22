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
import com.game.mcw.gameinformation.modle.News
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MyApapter3(layid: Int) : BaseMVAdapter<Game, MVViewHolder>(layid) {
    var options: RequestOptions

    init {
        val roundedCornersTransformation = RoundedCornersTransformation(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 5), 0, RoundedCornersTransformation.CornerType.ALL)
        options = RequestOptions().transforms(CenterCrop(), roundedCornersTransformation)
    }

    override fun convert(helper: MVViewHolder, item: Game) {
        helper.getBinding().setVariable(BR.game, item)
        helper.getBinding().executePendingBindings()
        Glide.with(mContext).load(item.img).apply(options).into(helper.getView(R.id.iv_pic))
    }


}