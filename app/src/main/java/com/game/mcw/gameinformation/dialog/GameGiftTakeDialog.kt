package com.game.mcw.gameinformation.dialog

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.WebActivity
import com.game.mcw.gameinformation.databinding.DialogGiftTakeBinding
import com.game.mcw.gameinformation.modle.GameGift
import com.qmuiteam.qmui.util.QMUIDisplayHelper

class GameGiftTakeDialog : DialogFragment() {
    lateinit var binding: DialogGiftTakeBinding
    lateinit var mGameGift: GameGift
    private lateinit var mCode: String
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
            mCode = it.getString("code", "")
            binding.code = mCode

        }
        binding.gameGift = mGameGift
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.startGame.setOnClickListener {
            activity?.let {
                WebActivity.goWeb(it, url = mGameGift.url!!)
                dismiss()
            }

        }
        binding.copy.setOnClickListener {
            val clipboardManager = activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Label", mCode)
            clipboardManager.primaryClip = clipData
            Toast.makeText(MyApplication.INSTANCE, "复制成功", Toast.LENGTH_SHORT).show()
            dismiss()

        }
    }
}