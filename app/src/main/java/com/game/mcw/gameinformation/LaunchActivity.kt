package com.game.mcw.gameinformation

import android.os.Bundle
import com.bumptech.glide.Glide
import com.game.mcw.gameinformation.databinding.ActivityLaunchBinding
import com.game.mcw.gameinformation.modle.IndexCommon
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.litepal.LitePal
import java.util.concurrent.TimeUnit

class LaunchActivity : BaseActivity<ActivityLaunchBinding>() {
    private lateinit var mDisposable: Disposable
    override fun getLayoutId(): Int {
        return R.layout.activity_launch
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.aty = this
        LitePal.findAllAsync(IndexCommon::class.java).listen { list ->
            list?.let {
                list.forEach {
                    if (it.isEffectived()) {
                        Glide.with(this@LaunchActivity).load(it.image).centerCrop().into(mBinding.ivLaunch)
                    }
                }
            }

        }
        mDisposable = Flowable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {}.doOnCancel { goToMain() }
                .doOnComplete { goToMain() }
                .subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
    }

    fun onSkipClick() {
        mDisposable.dispose()
    }

    private fun goToMain() {
        startActivity(MainActivity::class.java)
        finish()
    }

}