package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.adapter.base.BaseMVAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.modle.Video

class VideoAdapter(layId: Int) : BaseMVAdapter<Video, MVViewHolder>(layId) {

    override fun convert(helper: MVViewHolder, item: Video) {
        helper.getBinding().setVariable(BR.video, item)
        helper.getBinding().executePendingBindings()
    }


}