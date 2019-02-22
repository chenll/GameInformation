package com.game.mcw.gameinformation.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.BaseMVMultiItemAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.modle.News
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class NewsApapter() : BaseMVMultiItemAdapter<News, MVViewHolder>() {

    var options: RequestOptions

    init {
        val roundedCornersTransformation = RoundedCornersTransformation(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 5), 0, RoundedCornersTransformation.CornerType.ALL)
        options = RequestOptions().transforms(CenterCrop(), roundedCornersTransformation)
        addItemType(News.type_1, R.layout.test_item_1)
        addItemType(News.type_2, R.layout.test_item_1_1)
    }

    override fun convert(helper: MVViewHolder, item: News) {
        helper.getBinding().setVariable(BR.item, item)
        helper.getBinding().executePendingBindings()
        Glide.with(mContext).load(item.img).apply(options).into(helper.getView(R.id.iv_pic))
    }


}