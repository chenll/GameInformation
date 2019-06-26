package com.game.mcw.gameinformation.adapter

import android.support.v4.content.ContextCompat
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemGameGiftBinding
import com.game.mcw.gameinformation.modle.GameGift
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButtonDrawable

class GameGiftAdapter(layId: Int) : BaseMVAdapter<GameGift, MVViewHolder>(layId) {


    override fun convert(helper: MVViewHolder, item: GameGift) {
        helper.addOnClickListener(R.id.btn_get)
        val binding = helper.getBinding() as ItemGameGiftBinding
        binding.setVariable(BR.gameGift, item)
        val qmuiDrawable = binding.btnGet.background as QMUIRoundButtonDrawable
        qmuiDrawable.setBgData(ContextCompat.getColorStateList(mContext, if (item.status == 1) R.color.btn_bg_yellow else R.color.white))
        binding.executePendingBindings()

    }


}