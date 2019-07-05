package com.game.mcw.gameinformation.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemGameDetailPicBinding
import com.game.mcw.gameinformation.databinding.ItemMyBinding
import com.game.mcw.gameinformation.modle.Card
import com.game.mcw.gameinformation.utils.GlideUtil


class GameDetailPicAdapter(layId: Int) : BaseMVAdapter<String, MVViewHolder>(layId) {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun convert(helper: MVViewHolder, item: String) {
        val binding = helper.getBinding() as ItemGameDetailPicBinding
        GlideUtil.loadBorderRadiusGameIcon(item, binding.ivGamePic)
        binding.executePendingBindings()

    }


}