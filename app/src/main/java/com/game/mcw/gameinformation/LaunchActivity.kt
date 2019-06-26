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
    private lateinit var mIndexCommon: IndexCommon
    override fun getLayoutId(): Int {
        return R.layout.activity_launch
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.aty = this
        LitePal.findAllAsync(IndexCommon::class.java).listen { list ->
            if (list == null || list.isEmpty()) {
                return@listen
            }
            for (indexCommon in list) {
                if (indexCommon.isEffectived()) {
                    mIndexCommon = indexCommon
                    Glide.with(this@LaunchActivity).load(mIndexCommon.image).centerCrop().into(mBinding.ivLaunch)
                    mBinding.ivLaunch.setOnClickListener {
                        mIndexCommon.url?.let { it1 ->
                            if (!mDisposable.isDisposed) {
                                mDisposable.dispose()
                            }
                            WebActivity.goWeb(this@LaunchActivity, it1)
                            finish()
                        }
                    }
                    break
                }
            }


        }

        mDisposable = Flowable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCancel { goToMain() }
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