package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemItemNews2Binding
import com.game.mcw.gameinformation.modle.News
import com.game.mcw.gameinformation.utils.GlideUtil


class NewsSimply4Adapter(layId: Int) : BaseMVAdapter<News, MVViewHolder>(layId) {


    override fun convert(helper: MVViewHolder, item: News) {
        var binding = helper.getBinding() as ItemItemNews2Binding
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
        GlideUtil.loadBorderRadiusGameIcon(item.img,helper.getView(R.id.iv_pic))

    }

}