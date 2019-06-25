package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemCommodityBinding
import com.game.mcw.gameinformation.databinding.ItemGameGiftBinding
import com.game.mcw.gameinformation.modle.Commodity
import com.game.mcw.gameinformation.modle.GameGift

class CommodityAdapter(layId: Int) : BaseMVAdapter<Commodity, MVViewHolder>(layId) {


    override fun convert(helper: MVViewHolder, item: Commodity) {
        helper.addOnClickListener(R.id.btn_get)
        val binding = helper.getBinding() as ItemCommodityBinding
        binding.setVariable(BR.commodity, item)
        binding.executePendingBindings()

    }


}