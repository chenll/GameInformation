package com.game.mcw.gameinformation.adapter

import android.view.View
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.binding.GamePresenter
import com.game.mcw.gameinformation.databinding.ItemItemNews1Binding
import com.game.mcw.gameinformation.modle.News
import com.qmuiteam.qmui.util.QMUIDisplayHelper


class NewsSimply3Adapter(layId: Int) : BaseMVAdapter<News, MVViewHolder>(layId) {

    private val mGamePresenter = GamePresenter()

    override fun convert(helper: MVViewHolder, item: News) {
        var binding = helper.getBinding() as ItemItemNews1Binding
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.presenter, mGamePresenter)
        binding.executePendingBindings()

    }

    override fun createBaseViewHolder(view: View?): MVViewHolder {
        var holder = super.createBaseViewHolder(view)
        holder.itemView.layoutParams.width = QMUIDisplayHelper.getScreenWidth(mContext) / 4
        return holder
    }

}