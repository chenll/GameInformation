package com.game.mcw.gameinformation.adapter.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.game.mcw.gameinformation.R

abstract class BaseMVMultiItemAdapter<T : MultiItemEntity, K : MVViewHolder>(data: MutableList<T>?) : BaseMultiItemQuickAdapter<T, K>(data) {
    constructor() : this(null)

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        val binding: ViewDataBinding? = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
        return if (binding == null) {
            super.getItemView(layoutResId, parent)
        } else {
            val view: View = binding.root
            view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
            view
        }
    }

}