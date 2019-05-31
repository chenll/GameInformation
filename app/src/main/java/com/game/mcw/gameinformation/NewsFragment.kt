package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.game.mcw.gameinformation.adapter.NewsApapter
import com.game.mcw.gameinformation.databinding.FragmentHomeChild1Binding
import com.game.mcw.gameinformation.databinding.HeadNewsBinding
import com.game.mcw.gameinformation.modle.AppResponse
import com.game.mcw.gameinformation.modle.Game
import com.game.mcw.gameinformation.modle.NewsGroup
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.game.mcw.gameinformation.utils.GlideImageLoader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Type

class NewsFragment : BaseFragment() {
    private lateinit var mBinding: FragmentHomeChild1Binding
    private lateinit var mAdapter: NewsApapter
    private var page = 1
    private lateinit var mHeadBinding: HeadNewsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_child_1, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerView.isNestedScrollingEnabled = true
        mBinding.recyclerView.layoutManager = layoutManager
        mBinding.recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(activity).size(QMUIDisplayHelper.dp2px(activity, 1)).color(ContextCompat.getColor(activity!!, R.color.common_list_decoration)).build())
//        mAdapter = MyApapter1(R.layout.test_item_1)
        mAdapter = NewsApapter()
        mAdapter.bindToRecyclerView(mBinding.recyclerView)

        mHeadBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.head_news, null, false)
        mAdapter.setHeaderView(mHeadBinding.root)

//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)
        mAdapter.isFirstOnly(false)
        mAdapter.disableLoadMoreIfNotFullPage()
        mAdapter.setOnLoadMoreListener({
            loadData(false)
        }, mBinding.recyclerView)
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
//        loadData(false)
        loadHeadData()
        val type: Type = object : TypeToken<AppResponse<List<NewsGroup>>>() {}.type
        val s: AppResponse<List<NewsGroup>>? = Gson().fromJson("{\"code\":\"0\",\"msg\":\"操作成功\",\"data\":[{\"type\":3,\"title\":\"最近玩过\",\"news\":[{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"},{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"},{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"},{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"}]},{\"type\":4,\"title\":\"最近玩过\",\"news\":[{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"},{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"},{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"},{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"}]},{\"type\":1,\"news\":[{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"}]},{\"type\":1,\"news\":[{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"}]},{\"type\":1,\"news\":[{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"}]},{\"type\":1,\"news\":[{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"}]},{\"type\":1,\"news\":[{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"}]},{\"type\":1,\"news\":[{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"}]},{\"type\":1,\"news\":[{\"id\":7109,\"title\":\"《非常英雄》官方中文Steam正版分流下载发布！\",\"category\":\"单机资讯\",\"img\":\"http://imgs.ali213.net/news/2019/01/24/2019012453246796.jpg\",\"description\":\"小编今天为大家带来了《非常英雄（Unruly Heroes）》官方中文Steam正版分流下载，感兴趣的玩家们赶紧来看看吧。\",\"author\":\"小编：何者\",\"content\":\"\",\"views\":\"\"}]}]}", type)
        mAdapter.setNewData(s?.data)

    }

    private fun loadData(isRefresh: Boolean) {
        AppRepository.getIndexRepository().getNewsList(if (isRefresh) 1 else page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<List<NewsGroup>>() {
            override fun onNext(data: List<NewsGroup>) {
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
                mBinding.swipeRefreshLayout.isRefreshing = false
                mAdapter.loadMoreFail()

            }
        })
    }

    private fun loadHeadData() {
        AppRepository.getIndexRepository().getGameList(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<List<Game>>() {
            override fun onNext(data: List<Game>) {
                if (data.isNotEmpty()) {
                    mHeadBinding.viewpager.setImageLoader(GlideImageLoader())
                    mHeadBinding.viewpager.setImages(data)
                    mHeadBinding.viewpager.start()
                } else {
                    mAdapter.removeAllHeaderView()
                }
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mAdapter.removeAllHeaderView()

            }

        })
    }
}