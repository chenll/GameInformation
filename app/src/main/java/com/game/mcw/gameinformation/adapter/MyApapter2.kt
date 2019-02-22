package com.game.mcw.gameinformation.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.modle.News
import com.game.mcw.gameinformation.modle.Video
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MyApapter2(layid: Int) : BaseMVAdapter<Video, MVViewHolder>(layid) {
    var options: RequestOptions

    init {
        val roundedCornersTransformation = RoundedCornersTransformation(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 5), 0, RoundedCornersTransformation.CornerType.ALL)
        options = RequestOptions().transforms(CenterCrop(), roundedCornersTransformation)
    }

    override fun convert(helper: MVViewHolder, item: Video) {
        helper.getBinding().setVariable(BR.video, item)
        helper.getBinding().executePendingBindings()
//        Glide.with(mContext).load(item.).apply(options).into(helper.getView(R.id.iv_pic))
    }


}