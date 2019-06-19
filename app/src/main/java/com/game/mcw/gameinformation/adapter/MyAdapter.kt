package com.game.mcw.gameinformation.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemMyBinding
import com.game.mcw.gameinformation.modle.Card


class MyAdapter(layId: Int) : BaseMVAdapter<Card, MVViewHolder>(layId) {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun convert(helper: MVViewHolder, item: Card) {
        val binding = helper.getBinding() as ItemMyBinding
        binding.setVariable(BR.item, item)
        binding.ivCardIcon.setImageResource(item.icon)
        binding.executePendingBindings()

    }


}