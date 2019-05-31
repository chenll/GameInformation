package com.game.mcw.gameinformation.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.BaseMVMultiItemAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemItemNews1Binding
import com.game.mcw.gameinformation.databinding.ItemNews3Binding
import com.game.mcw.gameinformation.databinding.ItemNews3BindingImpl
import com.game.mcw.gameinformation.databinding.ItemNews4Binding
import com.game.mcw.gameinformation.modle.News
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class NewsSimply4Apapter(layid: Int) : BaseMVAdapter<News, MVViewHolder>(layid) {

    var options: RequestOptions

    init {
        val roundedCornersTransformation = RoundedCornersTransformation(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 5), 0, RoundedCornersTransformation.CornerType.ALL)
        options = RequestOptions().transforms(CenterCrop(), roundedCornersTransformation)

    }

    override fun convert(helper: MVViewHolder, item: News) {
        var binding = helper.getBinding() as ItemItemNews1Binding
        helper.getBinding().setVariable(BR.item, item)
        helper.getBinding().executePendingBindings()
        Glide.with(mContext).load(item.img).apply(options).into(helper.getView(com.game.mcw.gameinformation.R.id.iv_pic))
    }

}