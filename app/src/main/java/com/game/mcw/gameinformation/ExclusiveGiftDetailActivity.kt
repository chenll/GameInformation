package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import com.game.mcw.gameinformation.adapter.GameExclusiveGiftDetaliAdapter
import com.game.mcw.gameinformation.databinding.ActivityExclusiveGiftDeatilBinding
import com.game.mcw.gameinformation.databinding.CommonEmptyViewBinding
import com.game.mcw.gameinformation.modle.GameExclusiveGift
import com.game.mcw.gameinformation.modle.GameExclusiveGiftDetail
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.toolbar_home.view.*

class ExclusiveGiftDetailActivity : BaseActivity<ActivityExclusiveGiftDeatilBinding>() {
    private lateinit var mAdapter: GameExclusiveGiftDetaliAdapter
    private lateinit var emptyViewBinding: CommonEmptyViewBinding
    private lateinit var mGameExclusiveGift: GameExclusiveGift

    override fun getLayoutId(): Int {
        return R.layout.activity_exclusive_gift_deatil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGameExclusiveGift = intent.getParcelableExtra("GameExclusiveGift") as GameExclusiveGift
        initToolBar()
        mAdapter = GameExclusiveGiftDetaliAdapter(R.layout.item_game_gift)
        mAdapter.bindToRecyclerView(mBinding.recyclerView)
        emptyViewBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.common_empty_view, null, false)
        mAdapter.emptyView = emptyViewBinding.root
        mAdapter.isFirstOnly(false)
        mAdapter.disableLoadMoreIfNotFullPage()

        loadData()


    }

    override fun initStatusBar() {
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }

    private fun initToolBar() {
        setSupportActionBar(mBinding.includeToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.includeToolbar.toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        mBinding.includeToolbar.toolbar.toolbar_title.text = "礼包详情"
        mBinding.includeToolbar.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadData() {
        AppRepository.getIndexRepository().getByGameId(mGameExclusiveGift.id).subscribe(object : NetRespObserver<GameExclusiveGiftDetail>() {
            override fun onNext(data: GameExclusiveGiftDetail) {
                mAdapter.setNewData(data.gifts)
                mBinding.includeToolbar.toolbar.toolbar_title.text = data.name
                mAdapter.loadMoreEnd()

            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mAdapter.loadMoreFail()
            }
        })
    }
}