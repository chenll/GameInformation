package com.game.mcw.gameinformation.dialog

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.WebActivity
import com.game.mcw.gameinformation.databinding.DialogHomePopupBinding
import com.game.mcw.gameinformation.databinding.DialogQuitBinding
import com.game.mcw.gameinformation.modle.IndexCommon
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlin.math.min

class QuitPopUpDialog : DialogFragment() {
    lateinit var binding: DialogQuitBinding
    private var mPicheight: Int = 0
    private var mPicwidth: Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_quit, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val indexCommon = arguments!!.getParcelable<IndexCommon>("indexCommon")
//        mPicheight = arguments!!.getInt("height")
//        mPicwidth = arguments!!.getInt("width")

//        mPicheight = 100
//        mPicwidth = 100
//        binding.ivPopup.layoutParams.apply {
//            width = QMUIDisplayHelper.getScreenWidth(activity) * 3 / 4
//            height = min(width * mPicheight / mPicwidth, QMUIDisplayHelper.getScreenHeight(activity) * 9 / 10)
//        }
        activity?.let {
            Glide.with(it).load(indexCommon.image).into(binding.ivPopup)
            binding.ivPopup.setOnClickListener { v ->
                indexCommon.url?.let { url ->
                    WebActivity.goWeb(it, url)
                    dismiss()
                }
            }
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnSure.setOnClickListener {
            dismiss()
            activity!!.finish()
        }
    }
}