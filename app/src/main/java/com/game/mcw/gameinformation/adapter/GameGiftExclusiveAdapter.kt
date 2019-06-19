package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.modle.GameExclusiveGift

class GameGiftExclusiveAdapter(layId: Int) : BaseMVAdapter<GameExclusiveGift, MVViewHolder>(layId) {

    override fun convert(helper: MVViewHolder, item: GameExclusiveGift) {
        helper.getBinding().setVariable(BR.gameGift, item)
        helper.getBinding().executePendingBindings()

    }


}