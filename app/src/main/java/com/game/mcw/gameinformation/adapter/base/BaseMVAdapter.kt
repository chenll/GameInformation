package com.game.mcw.gameinformation.adapter.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.game.mcw.gameinformation.R

abstract class BaseMVAdapter<T, K : MVViewHolder>(layoutResId: Int, data: MutableList<T>?) : BaseQuickAdapter<T, K>(layoutResId, data) {

    constructor(layoutResId: Int) : this(layoutResId, null)

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