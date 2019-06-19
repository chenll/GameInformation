package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.binding.GamePresenter
import com.game.mcw.gameinformation.databinding.ItemItemNews2Binding
import com.game.mcw.gameinformation.modle.News


class NewsSimply4Adapter(layId: Int) : BaseMVAdapter<News, MVViewHolder>(layId) {
    private val mGamePresenter = GamePresenter()


    override fun convert(helper: MVViewHolder, item: News) {
        var binding = helper.getBinding() as ItemItemNews2Binding
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.presenter, mGamePresenter)
        binding.executePendingBindings()
    }

}