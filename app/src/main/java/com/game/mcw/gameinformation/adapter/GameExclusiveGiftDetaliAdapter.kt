package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemGameGiftBinding
import com.game.mcw.gameinformation.databinding.ItemGameGiftDetailBinding
import com.game.mcw.gameinformation.modle.GameGift

class GameExclusiveGiftDetaliAdapter(layId: Int) : BaseMVAdapter<GameGift, MVViewHolder>(layId) {


    override fun convert(helper: MVViewHolder, item: GameGift) {
        helper.addOnClickListener(R.id.btn_get)
        val binding = helper.getBinding() as ItemGameGiftDetailBinding
        binding.setVariable(BR.gameGift, item)
        binding.executePendingBindings()

    }


}