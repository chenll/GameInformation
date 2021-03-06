package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.game.mcw.gameinformation.adapter.GameAdapter
import com.game.mcw.gameinformation.databinding.FragmentHomeChild3Binding
import com.game.mcw.gameinformation.modle.Game
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameFragment : BaseFragment() {
    private lateinit var mBinding: FragmentHomeChild3Binding
    private lateinit var mAdapter: GameAdapter
    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_child_3, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerView.isNestedScrollingEnabled = true
        mBinding.recyclerView.layoutManager = layoutManager
//        mBinding.recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(activity).size(QMUIDisplayHelper.dp2px(activity, 1)).color(ContextCompat.getColor(activity!!, R.color.common_list_decoration)).build())
        mAdapter = GameAdapter(R.layout.test_item_3)
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
        mAdapter.setOnItemChildClickListener { _, _, position ->
            activity?.let { WebActivity.goWeb(it, mAdapter.getItem(position)!!.url,mAdapter.getItem(position)!!.name) }
        }
    }

    private fun loadData(isRefresh: Boolean) {
        AppRepository.getIndexRepository().getGameList(if (isRefresh) 1 else page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<List<Game>>() {
            override fun onNext(data: List<Game>) {
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
                mAdapter.loadMoreFail()
                mBinding.swipeRefreshLayout.isRefreshing = false


            }
        })
    }
}