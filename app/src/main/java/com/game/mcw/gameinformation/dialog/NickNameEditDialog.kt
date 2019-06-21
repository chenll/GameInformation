package com.game.mcw.gameinformation.dialog

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.game.mcw.gameinformation.BaseActivity
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.databinding.DialogEditNicknameBinding
import com.game.mcw.gameinformation.manager.MyUserManager
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.dialog_edit_nickname.*

class NickNameEditDialog : DialogFragment() {
    lateinit var binding: DialogEditNicknameBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_edit_nickname, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.save.isEnabled = false
        binding.save.isClickable = false
        binding.etNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val nickName = et_nickname.text.toString()
                if (TextUtils.isEmpty(nickName)) {
                    binding.save.isEnabled = false
                    binding.save.isClickable = false
                    binding.tvCount.text = "0/11"
                } else {
                    if (nickName == MyUserManager.instance.userBean?.nickname) {
                        binding.save.isEnabled = false
                        binding.save.isClickable = false
                    } else {
                        binding.save.isEnabled = true
                        binding.save.isClickable = true
                        binding.tvCount.text = "${nickName.length}/11"

                    }
                }
            }
        })
        binding.save.setOnClickListener {
            (activity as BaseActivity<*>).showLoading()
            AppRepository.getUserRepository().editUserMessage(nickname = binding.etNickname.text.toString())
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
