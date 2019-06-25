package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import com.game.mcw.gameinformation.adapter.CommodityAdapter
import com.game.mcw.gameinformation.databinding.ActivityCommodityListBinding
import com.game.mcw.gameinformation.databinding.CommonEmptyViewBinding
import com.game.mcw.gameinformation.dialog.GameGiftTakeDialog
import com.game.mcw.gameinformation.manager.MyUserManager
import com.game.mcw.gameinformation.modle.Commodity
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.toolbar_home.view.*

class CommodityListActivity : BaseActivity<ActivityCommodityListBinding>() {
    private lateinit var mAdapter: CommodityAdapter
    private lateinit var emptyViewBinding: CommonEmptyViewBinding
    private var page: Int = 1

    override fun getLayoutId(): Int {
        return R.layout.activity_commodity_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        MyUserManager.instance.userBean?.let { mBinding.tvUserSoure.text = "${it.totalScore}${""}" }
        emptyViewBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.common_empty_view, null, false)
        mAdapter = CommodityAdapter(R.layout.item_commodity).apply {
            bindToRecyclerView(mBinding.recyclerView)
            emptyView = emptyViewBinding.root
            isFirstOnly(false)
            disableLoadMoreIfNotFullPage()
            setOnLoadMoreListener({ loadData(false) }, mBinding.recyclerView)
            setOnItemChildClickListener { adapter, _, position ->
                if (MyUserManager.instance.userBean == null) {
                    startActivity(LoginActivity::class.java)
                    return@setOnItemChildClickListener
                }

//                showLoading()
//                AppRepository.getIndexRepository().takeGift((adapter.getItem(position) as GameGift).id).subscribe(object : NetRespObserver<String>() {
//                    override fun onNext(code: String) {
//                        hideLoading()
//                        GameGiftTakeDialog().apply {
//                            arguments = Bundle().apply {
//                                putParcelable("gamegift", adapter.getItem(position) as GameGift)
//                                putString("code", code)
//                            }
//                            show(this@CommodityListActivity.supportFragmentManager, "gamegifttakedialog")
//                        }
//                    }
//
//                    override fun onError(e: Throwable) {
//                        super.onError(e)
//                        hideLoading()
//                    }
//                })
            }
        }

        mBinding.swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
        loadData(true)


    }


    private fun initToolBar() {
        setSupportActionBar(mBinding.includeToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.includeToolbar.toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent))
        mBinding.includeToolbar.toolbar.toolbar_title.setTextColor(ContextCompat.getColor(this, R.color.white))
        mBinding.includeToolbar.toolbar.toolbar_title.text = "积分商城"
        mBinding.includeToolbar.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadData(isRefresh: Boolean) {
        AppRepository.getIndexRepository().getCommodityList(if (isRefresh) 1 else page).subscribe(object : NetRespObserver<List<Commodity>>() {
            override fun onNext(data: List<Commodity>) {
                mBinding.swipeRefreshLayout.isRefreshing = false
                if (data.isNotEmpty()) {
                    if (isRefresh) {
                        mAdapter.setNewData(data)
                        page = 2
                    } else {
                        mAdapter.addData(data)
                        page++
                    }
                    mAdapter.loadMoreComplete()
                } else {
                    mAdapter.loadMoreEnd()
                }


            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mAdapter.loadMoreFail()
                mBinding.swipeRefreshLayout.isRefreshing = false

            }
        })
    }

    override fun initStatusBar() {
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarDarkMode(this)

    }
}