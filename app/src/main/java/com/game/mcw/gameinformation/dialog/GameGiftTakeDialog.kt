package com.game.mcw.gameinformation.dialog

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.databinding.DialogGiftTakeBinding
import com.game.mcw.gameinformation.databinding.DialogHomePopupBinding
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.modle.IndexCommon
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlin.math.min

class GameGiftTakeDialog : DialogFragment() {
    lateinit var binding: DialogGiftTakeBinding
    lateinit var mGameGift: GameGift
    lateinit var mCode: String
    override fun onStart() {
        super.onStart()
        dialog.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.attributes = it.attributes.apply {
                gravity = Gravity.CENTER
                width = QMUIDisplayHelper.getScreenWidth(activity) * 10 / 12
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_gift_take, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            mGameGift = it.getParcelable("gamegift")
            mCode = it.getString("code")

        }
        binding.gameGift = mGameGift
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }
}