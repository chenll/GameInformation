package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.game.mcw.gameinformation.adapter.VideoApapter
import com.game.mcw.gameinformation.databinding.FragmentHomeChild2Binding
import com.game.mcw.gameinformation.modle.Video
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeChild2Fragment : BaseFragment() {
    private lateinit var mBinding: FragmentHomeChild2Binding
    private lateinit var mAdapter: VideoApapter
    private var page = 1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_child_2, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerView.isNestedScrollingEnabled = true
        mBinding.recyclerView.layoutManager = layoutManager
        mBinding.recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(activity).size(QMUIDisplayHelper.dp2px(activity, 1)).color(ContextCompat.getColor(activity!!, R.color.common_list_decoration)).build())
        mAdapter = VideoApapter(R.layout.test_item_2)
        mAdapter.bindToRecyclerView(mBinding.recyclerView)
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)
        mAdapter.isFirstOnly(false)
        mAdapter.disableLoadMoreIfNotFullPage()
        mAdapter.setOnLoadMoreListener({
            loadData(false)
        }, mBinding.recyclerView)
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
        loadData(false)
    }

    private fun loadData(isRefresh: Boolean) {
        AppRepository.getIndexRepository().getVideoList(if (isRefresh) 1 else page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<List<Video>>() {
            override fun onNext(data: List<Video>) {
                if (!data.isEmpty()) {
                    if (isRefresh) {
                        mAdapter.setNewData(data)
                        page = 2
                    } else {
                        mAdapter.addData(data)
                        page++
                    }
                    mAdapter.notifyDataSetChanged()
                    mAdapter.loadMoreComplete()
                    mBinding.swipeRefreshLayout.isRefreshing = false
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