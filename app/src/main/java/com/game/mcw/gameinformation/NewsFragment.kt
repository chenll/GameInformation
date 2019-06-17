package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.game.mcw.gameinformation.adapter.NewsAdapter
import com.game.mcw.gameinformation.databinding.CommonEmptyViewBinding
import com.game.mcw.gameinformation.databinding.FragmentHomeChild1Binding
import com.game.mcw.gameinformation.databinding.HeadNewsBinding
import com.game.mcw.gameinformation.modle.IndexResource
import com.game.mcw.gameinformation.modle.News
import com.game.mcw.gameinformation.modle.NewsGroup
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.game.mcw.gameinformation.utils.GlideImageLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsFragment : BaseFragment() {
    private lateinit var mBinding: FragmentHomeChild1Binding
    private lateinit var mAdapter: NewsAdapter
    private var page = 1
    private lateinit var mHeadBinding: HeadNewsBinding
    private lateinit var emptyViewBinding: CommonEmptyViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_child_1, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerView.isNestedScrollingEnabled = true
//        mBinding.recyclerView.layoutManager = layoutManager
//        mBinding.recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(activity).size(QMUIDisplayHelper.dp2px(activity, 1)).color(ContextCompat.getColor(activity!!, R.color.common_list_decoration)).build())
//        mAdapter = MyApapter1(R.layout.test_item_1)
        mAdapter = NewsAdapter()
        mAdapter.bindToRecyclerView(mBinding.recyclerView)

        mHeadBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.head_news, null, false)
        mAdapter.setHeaderView(mHeadBinding.root)
        emptyViewBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.common_empty_view, null, false)
        mAdapter.emptyView = emptyViewBinding.root
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
        loadHeadData()


    }


    private fun loadData(isRefresh: Boolean) {
        AppRepository.getIndexRepository().getNewsList(if (isRefresh) 1 else page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<List<NewsGroup>>() {
            override fun onNext(data: List<NewsGroup>) {
                if (data.isNotEmpty()) {
                    val dataFinal = ArrayList<NewsGroup>()
                    for (newsGroup in data) {
                        if (newsGroup.type == 3) {
                            for (news in newsGroup.data) {
                                val newsfinal = ArrayList<News>()
                                newsfinal.add(news)
                                val newgroupNew = NewsGroup(newsGroup.type, newsGroup.name, newsfinal)
                                dataFinal.add(newgroupNew)
                            }
                        } else {
                            dataFinal.add(newsGroup)
                        }
                    }
                    mAdapter.setNewData(dataFinal)
                    mAdapter.notifyDataSetChanged()
                    mAdapter.loadMoreComplete()
                    mBinding.swipeRefreshLayout.isRefreshing = false
                    mAdapter.loadMoreEnd()
//                    if (isRefresh) {
//                        mAdapter.setNewData(data)
//                        page = 2
//                    } else {
//                        mAdapter.addData(data)
//                        page++
//                    }
//                    mAdapter.notifyDataSetChanged()
//                    mAdapter.loadMoreComplete()
//                    mBinding.swipeRefreshLayout.isRefreshing = false
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
        AppRepository.getIndexRepository().getInit().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<IndexResource>() {
            override fun onNext(data: IndexResource) {
                if (data.banners.isNotEmpty()) {
                    mHeadBinding.viewpager.setImageLoader(GlideImageLoader())
                    mHeadBinding.viewpager.setImages(data.banners)
                    mHeadBinding.viewpager.setOnBannerListener {
                        Log.e("aaa", "=======1")
                        activity?.let { it1 -> WebActivity.goWeb(it1, data.banners[it].url) }
                    }
                    mHeadBinding.viewpager.start()

                } else {
                    mAdapter.removeAllHeaderView()
                }


                if (data.starts.isNotEmpty()) {
                    for (start in data.starts) {
                        Log.e("aaa", "开始时间${start.startDate} 结束时间${start.endDate}")
//                        if (System.currentTimeMillis() > start.startDate && System.currentTimeMillis() < start.endDate) {
//                            Glide.with(activity!!).load(start.image).diskCacheStrategy(DiskCacheStrategy.RESOURCE).preload()
//                        }

                    }
                }
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mAdapter.removeAllHeaderView()

            }

        })
    }
}