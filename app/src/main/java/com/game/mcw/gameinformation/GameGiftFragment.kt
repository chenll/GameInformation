package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.game.mcw.gameinformation.adapter.GameGiftApapter
import com.game.mcw.gameinformation.adapter.MyApapter3
import com.game.mcw.gameinformation.databinding.FragmentGameBinding
import com.game.mcw.gameinformation.databinding.FragmentGameGiftBinding
import com.game.mcw.gameinformation.databinding.FragmentHomeChild3Binding
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameGiftFragment : BaseFragment() {
    private lateinit var mBinding: FragmentGameGiftBinding
    private lateinit var mAdapter: GameGiftApapter
    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_gift, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerView.isNestedScrollingEnabled = true
        mBinding.recyclerView.layoutManager = layoutManager
        mBinding.recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(activity).size(QMUIDisplayHelper.dp2px(activity, 1)).color(ContextCompat.getColor(activity!!, R.color.common_list_decoration)).build())
        mAdapter = GameGiftApapter(R.layout.item_game_gift)
        mAdapter.bindToRecyclerView(mBinding.recyclerView)
        mAdapter.isFirstOnly(false)
        mAdapter.disableLoadMoreIfNotFullPage()
        mAdapter.setOnLoadMoreListener({
            loadData(false)
        }, mBinding.recyclerView)
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
        loadData(false)
//        mAdapter.setOnItemChildClickListener { adapter, view, position ->
//            activity?.let { WebActivity.goWeb(it, mAdapter.getItem(position)!!.url) }
//        }
    }

    private fun loadData(isRefresh: Boolean) {
        AppRepository.getIndexRepository().getGameGiftList(if (isRefresh) 1 else page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<List<GameGift>>() {
            override fun onNext(data: List<GameGift>) {
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