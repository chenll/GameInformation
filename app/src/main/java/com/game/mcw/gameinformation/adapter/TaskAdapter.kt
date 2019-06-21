package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemGameGiftBinding
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.modle.Task

class TaskAdapter(layId: Int) : BaseMVAdapter<Task, MVViewHolder>(layId) {


    override fun convert(helper: MVViewHolder, item: Task) {
        helper.addOnClickListener(R.id.btn_get)
        val binding = helper.getBinding()
        binding.setVariable(BR.task, item)
        binding.executePendingBindings()

    }


}