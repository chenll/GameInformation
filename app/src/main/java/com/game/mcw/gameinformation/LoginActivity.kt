package com.game.mcw.gameinformation

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Switch
import android.widget.Toast
import com.game.mcw.gameinformation.databinding.ActivityLoginBinding
import com.game.mcw.gameinformation.manager.MyUserManager
import com.game.mcw.gameinformation.modle.UserBean
import com.game.mcw.gameinformation.modle.VcodeResponse
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.litepal.LitePal
import org.litepal.extension.deleteAll
import org.litepal.extension.findAsync
import org.litepal.extension.findFirstAsync

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    var isVCodeSended: Boolean = false
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.activity = this

    }

    override fun initStatusBar() {
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }

    fun click(v: View) {


        if (v.id == R.id.btn_get) {
            if (TextUtils.isEmpty(mBinding.etMobile.text)) {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show()
                return
            }
            AppRepository.getUserRepository().getVcode4Login(mBinding.etMobile.text.toString()).subscribe(object : NetRespObserver<VcodeResponse>() {
                override fun onNext(data: VcodeResponse) {
                    Toast.makeText(this@LoginActivity, "验证码发生${if (data.code == "0") "成功" else "失败"}", Toast.LENGTH_SHORT).show()
                    isVCodeSended = (data.code == "0")
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    Toast.makeText(this@LoginActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            })

        }
        if (v.id == R.id.btn_login) {
            if (TextUtils.isEmpty(mBinding.etMobile.text)) {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show()
                return
            }
            if (!isVCodeSended) {
                Toast.makeText(this, "请先获取验证码", Toast.LENGTH_SHORT).show()
                return
            }
            if (TextUtils.isEmpty(mBinding.etVcode.text)) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show()
                return
            }
            AppRepository.getUserRepository().login(mBinding.etMobile.text.toString(), mBinding.etVcode.text.toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<UserBean>() {
                override fun onNext(data: UserBean) {
                    MyUserManager.instance.updateUser(data)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    Toast.makeText(this@LoginActivity, "${e.message}", Toast.LENGTH_SHORT).show()

                }


            })
        }

    }
}