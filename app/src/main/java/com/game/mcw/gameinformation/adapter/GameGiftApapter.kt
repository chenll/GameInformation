package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemGameGiftBinding
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.utils.GlideUtil

class GameGiftApapter(layid: Int) : BaseMVAdapter<GameGift, MVViewHolder>(layid) {


    override fun convert(helper: MVViewHolder, item: GameGift) {
        val binding = helper.getBinding() as ItemGameGiftBinding
        binding.setVariable(BR.gameGift, item)
        binding.executePendingBindings()
        GlideUtil.loadBorderRadiusGameIcon("", binding.ivGameIcon)

    }


}