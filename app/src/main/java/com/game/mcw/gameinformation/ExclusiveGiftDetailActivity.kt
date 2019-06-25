package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import com.game.mcw.gameinformation.adapter.GameExclusiveGiftDetaliAdapter
import com.game.mcw.gameinformation.databinding.ActivityExclusiveGiftDeatilBinding
import com.game.mcw.gameinformation.databinding.CommonEmptyViewBinding
import com.game.mcw.gameinformation.databinding.HeadGiftDetailBinding
import com.game.mcw.gameinformation.dialog.GameGiftTakeDialog
import com.game.mcw.gameinformation.manager.MyUserManager
import com.game.mcw.gameinformation.modle.GameExclusiveGift
import com.game.mcw.gameinformation.modle.GameExclusiveGiftDetail
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.toolbar_home.view.*

class ExclusiveGiftDetailActivity : BaseActivity<ActivityExclusiveGiftDeatilBinding>() {
    private lateinit var mAdapter: GameExclusiveGiftDetaliAdapter
    private lateinit var emptyViewBinding: CommonEmptyViewBinding
    private lateinit var headViewBinding: HeadGiftDetailBinding
    private lateinit var mGameExclusiveGift: GameExclusiveGift

    override fun getLayoutId(): Int {
        return R.layout.activity_exclusive_gift_deatil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGameExclusiveGift = intent.getParcelableExtra("GameExclusiveGift") as GameExclusiveGift
        initToolBar()
        emptyViewBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.common_empty_view, null, false)
        headViewBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.head_gift_detail, null, false)
        headViewBinding.gameGift = mGameExclusiveGift
        mAdapter = GameExclusiveGiftDetaliAdapter(R.layout.item_game_gift).apply {
            bindToRecyclerView(mBinding.recyclerView)
            emptyView = emptyViewBinding.root
            addHeaderView(headViewBinding.root)
            isFirstOnly(false)
            disableLoadMoreIfNotFullPage()
            setOnItemChildClickListener { adapter, _, position ->
                if (MyUserManager.instance.userBean == null) {
                    startActivity(LoginActivity::class.java)
                    return@setOnItemChildClickListener
                }

                showLoading()
                AppRepository.getIndexRepository().takeGift((adapter.getItem(position) as GameGift).id).subscribe(object : NetRespObserver<String>() {
                    override fun onNext(code: String) {
                        hideLoading()
                        GameGiftTakeDialog().apply {
                            arguments = Bundle().apply {
                                putParcelable("gamegift", adapter.getItem(position) as GameGift)
                                putString("code", code)
                            }
                            show(this@ExclusiveGiftDetailActivity.supportFragmentManager, "gamegifttakedialog")
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        hideLoading()
                    }
                })
            }
        }
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