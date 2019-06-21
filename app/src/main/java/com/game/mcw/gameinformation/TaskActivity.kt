package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import com.game.mcw.gameinformation.adapter.GameExclusiveGiftDetaliAdapter
import com.game.mcw.gameinformation.adapter.TaskAdapter
import com.game.mcw.gameinformation.databinding.ActivityExclusiveGiftDeatilBinding
import com.game.mcw.gameinformation.databinding.ActivityTaskBinding
import com.game.mcw.gameinformation.databinding.CommonEmptyViewBinding
import com.game.mcw.gameinformation.dialog.GameGiftTakeDialog
import com.game.mcw.gameinformation.modle.GameExclusiveGift
import com.game.mcw.gameinformation.modle.GameExclusiveGiftDetail
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.modle.Task
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.toolbar_home.view.*

class TaskActivity : BaseActivity<ActivityTaskBinding>() {
    private lateinit var mAdapter: TaskAdapter
    private lateinit var emptyViewBinding: CommonEmptyViewBinding
    private var page = 1

    override fun getLayoutId(): Int {
        return R.layout.activity_task
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        emptyViewBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.common_empty_view, null, false)
        mAdapter = TaskAdapter(R.layout.item_task).apply {
            bindToRecyclerView(mBinding.recyclerView)
            emptyView = emptyViewBinding.root
            isFirstOnly(false)
            disableLoadMoreIfNotFullPage()
            setOnLoadMoreListener({ loadData(false) }, mBinding.recyclerView)

        }
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
        loadData(false)


    }

    private fun initToolBar() {
        setSupportActionBar(mBinding.includeToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.includeToolbar.toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        mBinding.includeToolbar.toolbar.toolbar_title.text = "任务中心"
        mBinding.includeToolbar.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadData(isRefresh: Boolean) {
        AppRepository.getIndexRepository().getTaskList(if (isRefresh) 1 else page).subscribe(object : NetRespObserver<List<Task>>() {
            override fun onNext(data: List<Task>) {
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
                mBinding.swipeRefreshLayout.isRefreshing = false
                mAdapter.loadMoreFail()
            }
        })
    }
}