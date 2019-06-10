package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemGameGiftExclusiveBinding
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.utils.GlideUtil

class GameGiftExclusiveApapter(layid: Int) : BaseMVAdapter<GameGift, MVViewHolder>(layid) {

    override fun convert(helper: MVViewHolder, item: GameGift) {
        var binding = helper.getBinding() as ItemGameGiftExclusiveBinding

        helper.getBinding().setVariable(BR.gameGift, item)
        helper.getBinding().executePendingBindings()
        GlideUtil.loadBorderRadiusGameIcon("", binding.ivGameIcon)

    }


}