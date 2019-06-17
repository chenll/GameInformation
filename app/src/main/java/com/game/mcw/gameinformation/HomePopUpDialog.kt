package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.game.mcw.gameinformation.databinding.DialogHomePopupBinding
import com.game.mcw.gameinformation.modle.IndexCommon

class HomePopUpDialog : DialogFragment() {
    lateinit var binding: DialogHomePopupBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_home_popup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var indexCommon = arguments!!.getParcelable<IndexCommon>("indexCommon")
        activity?.let { Glide.with(it).load(indexCommon.image).into(binding.ivPopup) }

    }
}