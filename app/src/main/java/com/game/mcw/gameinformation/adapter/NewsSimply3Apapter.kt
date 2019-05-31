package com.game.mcw.gameinformation.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemItemNews1Binding
import com.game.mcw.gameinformation.modle.News
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class NewsSimply3Apapter(layid: Int) : BaseMVAdapter<News, MVViewHolder>(layid) {

    var options: RequestOptions

    init {
        val roundedCornersTransformation = RoundedCornersTransformation(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 5), 0, RoundedCornersTransformation.CornerType.ALL)
        options = RequestOptions().transforms(CenterCrop(), roundedCornersTransformation)

    }

    override fun convert(helper: MVViewHolder, item: News) {
        var binding = helper.getBinding() as ItemItemNews1Binding
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
        Glide.with(mContext).load(item.img).apply(options).into(helper.getView(com.game.mcw.gameinformation.R.id.iv_pic))
    }

    override fun createBaseViewHolder(view: View?): MVViewHolder {
        var holder = super.createBaseViewHolder(view)
        holder.itemView.layoutParams.width = QMUIDisplayHelper.getScreenWidth(mContext) / 4
        return holder
    }

}