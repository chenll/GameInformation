package com.game.mcw.gameinformation

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.game.mcw.gameinformation.adapter.GameGiftExclusiveAdapter
import com.game.mcw.gameinformation.adapter.GameGiftAdapter
import com.game.mcw.gameinformation.databinding.FragmentGameGiftBinding
import com.game.mcw.gameinformation.modle.GameExclusiveGift
import com.game.mcw.gameinformation.modle.GameGift
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GameGiftFragment : BaseFragment() {
    private lateinit var mBinding: FragmentGameGiftBinding
    private lateinit var mAdapter: GameGiftAdapter
    private lateinit var mExclusiveAdapter: GameGiftExclusiveAdapter
    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_gift, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initExclusiveRecyclerView()

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerView.isNestedScrollingEnabled = true
        mBinding.recyclerView.layoutManager = layoutManager
        mBinding.recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(activity).size(QMUIDisplayHelper.dp2px(activity, 6)).color(ContextCompat.getColor(activity!!, R.color.transparent)).build())
        mAdapter = GameGiftAdapter(R.layout.item_game_gift)
        mAdapter.bindToRecyclerView(mBinding.recyclerView)
        mAdapter.isFirstOnly(false)
        mAdapter.disableLoadMoreIfNotFullPage()
        mAdapter.setOnLoadMoreListener({
            loadData(false)
        }, mBinding.recyclerView)
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            showLoading()
            AppRepository.getIndexRepository().takeGift((adapter.getItem(position) as GameGift).id).subscribe(object : NetRespObserver<String>() {
                override fun onNext(t: String) {
                    hideLoading()
                    Toast.makeText(activity, "领取成功", Toast.LENGTH_SHORT).show()
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    hideLoading()
                    Toast.makeText(activity, "${e.message}", Toast.LENGTH_SHORT).show()
                }


            })
        }
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
        loadData(false)
//        mAdapter.setOnItemChildClickListener { adapter, view, position ->
//            activity?.let { WebActivity.goWeb(it, mAdapter.getItem(position)!!.url) }
//        }
        loadExclusiveGiftData()
    }

    private fun initExclusiveRecyclerView() {
        val layoutManagerExclusive = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mBinding.rvExclusive.layoutManager = layoutManagerExclusive
        mBinding.rvExclusive.addItemDecoration(VerticalDividerItemDecoration.Builder(activity).size(QMUIDisplayHelper.dp2px(activity, 10)).color(ContextCompat.getColor(activity!!, R.color.transparent)).build())
        mExclusiveAdapter = GameGiftExclusiveAdapter(R.layout.item_game_gift_exclusive)
        mExclusiveAdapter.bindToRecyclerView(mBinding.rvExclusive)
        mExclusiveAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity, ExclusiveGiftDetailActivity::class.java)
            intent.putExtra("GameExclusiveGift", adapter.getItem(position) as GameExclusiveGift)
            startActivity(intent)
        }
    }

    private fun loadExclusiveGiftData() {
        AppRepository.getIndexRepository().getGameExclusiveGiftList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<List<GameExclusiveGift>>() {
            override fun onNext(data: List<GameExclusiveGift>) {
                if (data.isNotEmpty()) {
                    mExclusiveAdapter.setNewData(data)
                } else {
                    mExclusiveAdapter.setNewData(null)
                }
            }


        })
    }


    private fun loadData(isRefresh: Boolean) {
        AppRepository.getIndexRepository().getGameGiftList(if (isRefresh) 1 else page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<List<GameGift>>() {
            override fun onNext(data: List<GameGift>) {
                if (data.isNotEmpty()) {
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