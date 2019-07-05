package com.game.mcw.gameinformation

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.game.mcw.gameinformation.databinding.ActivityGameDetailBinding
import com.game.mcw.gameinformation.modle.News
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.game.mcw.gameinformation.utils.GlideUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

class GameDetailActivity : BaseActivity<ActivityGameDetailBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_game_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        val gameId = intent.getIntExtra("gameid", -1)
        AppRepository.getIndexRepository().getGameDetils(gameId).subscribe(object : NetRespObserver<News>() {
            override fun onNext(t: News) {
                GlideUtil.loadBorderRadiusGameIcon(t.icon, mBinding.ivGameIcon)
                mBinding.game = t
            }
        })

    }

    private fun initToolBar() {
        setSupportActionBar(mBinding.includeToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.includeToolbar.toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        mBinding.includeToolbar.toolbar.setNavigationOnClickListener {
            finish()
        }
        mBinding.includeToolbar.toolbarTitle.text = "详情"
    }

    override fun initStatusBar() {
        QMUIStatusBarHelper.setStatusBarLightMode(this)

    }

}