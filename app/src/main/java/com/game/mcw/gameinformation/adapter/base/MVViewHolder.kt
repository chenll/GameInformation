package com.game.mcw.gameinformation.adapter.base

import android.databinding.ViewDataBinding
import android.view.View
import com.chad.library.adapter.base.BaseViewHolder
import com.game.mcw.gameinformation.R

open class MVViewHolder(view: View?) : BaseViewHolder(view) {
    fun getBinding(): ViewDataBinding = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
}