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
import android.widget.Toast
import com.game.mcw.gameinformation.BaseActivity
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.databinding.DialogSexChoseBinding
import com.game.mcw.gameinformation.manager.MyUserManager
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIDisplayHelper

class SexSelectDialog : DialogFragment() {
    lateinit var binding: DialogSexChoseBinding

    override fun onStart() {
        super.onStart()
        dialog.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.attributes = it.attributes.apply {
                gravity = Gravity.CENTER
                width = QMUIDisplayHelper.getScreenWidth(activity) * 3 / 4
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_sex_chose, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rbBoy.isChecked = MyUserManager.instance.userBean?.getSexName().equals("男")
        binding.rbGril.isChecked = MyUserManager.instance.userBean?.getSexName().equals("女")
        binding.save.isEnabled = false
        binding.rgSex.setOnCheckedChangeListener { _, _ ->
            if (binding.rbBoy.isChecked) {
                binding.save.isEnabled = !MyUserManager.instance.userBean?.getSexName().equals("男")
                binding.save.isClickable = !MyUserManager.instance.userBean?.getSexName().equals("男")
            }
            if (binding.rbGril.isChecked) {
                binding.save.isEnabled = !MyUserManager.instance.userBean?.getSexName().equals("女")
                binding.save.isClickable = !MyUserManager.instance.userBean?.getSexName().equals("女")
            }

        }
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.save.setOnClickListener {
            (activity as BaseActivity<*>).showLoading()
            AppRepository.getUserRepository().editUserMessage(sex = if (binding.rbBoy.isChecked) "1" else "2")
                    .subscribe(object : NetRespObserver<String>() {
                        override fun onNext(user: String) {
                            (activity as BaseActivity<*>).hideLoading()
                            Toast.makeText(MyApplication.INSTANCE, "修改成功", Toast.LENGTH_SHORT).show()
                            MyUserManager.instance.autoAsyncUpdateUserMessage(true)
                            dismiss()

                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                            (activity as BaseActivity<*>).hideLoading()
                        }
                    })

        }
    }
}
