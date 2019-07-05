package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.game.mcw.gameinformation.adapter.NewsLoadMoreView
import com.game.mcw.gameinformation.adapter.NewsAdapter
import com.game.mcw.gameinformation.databinding.CommonEmptyViewBinding
import com.game.mcw.gameinformation.databinding.FragmentHomeChild1Binding
import com.game.mcw.gameinformation.databinding.HeadNewsBinding
import com.game.mcw.gameinformation.dialog.HomePopUpDialog
import com.game.mcw.gameinformation.modle.IndexCommon
import com.game.mcw.gameinformation.modle.IndexResource
import com.game.mcw.gameinformation.modle.News
import com.game.mcw.gameinformation.modle.NewsGroup
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.game.mcw.gameinformation.utils.GlideImageLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.litepal.LitePal

class NewsFragment : BaseFragment() {
    private lateinit var mBinding: FragmentHomeChild1Binding
    private lateinit var mAdapter: NewsAdapter
    private var page = 1
    private lateinit var mHeadBinding: HeadNewsBinding
    private lateinit var emptyViewBinding: CommonEmptyViewBinding
    private lateinit var mLoadMoreView: NewsLoadMoreView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_child_1, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.recyclerView.isNestedScrollingEnabled = true
        mHeadBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.head_news, null, false)
        emptyViewBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.common_empty_view, null, false)
        mLoadMoreView = NewsLoadMoreView()
        mAdapter = NewsAdapter().apply {
            setLoadMoreView(mLoadMoreView)
            bindToRecyclerView(mBinding.recyclerView)
            setHeaderView(mHeadBinding.root)
            emptyView = emptyViewBinding.root
            isFirstOnly(false)
//            openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)
            disableLoadMoreIfNotFullPage()


        }
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
        loadData(false)
        loadHeadData()


    }


    private fun loadData(isRefresh: Boolean) {
        AppRepository.getIndexRepository().getNewsList(if (isRefresh) 1 else page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<List<NewsGroup>>() {
            override fun onNext(data: List<NewsGroup>) {
                if (data.isEmpty()) {
                    with(mAdapter) {
                        setOnLoadMoreListener({
                            Toast.makeText(MyApplication.INSTANCE, "敬请期待~", Toast.LENGTH_SHORT).show()
                            loadMoreEnd()
                        }, mBinding.recyclerView)
                        loadMoreEnd()
                        enableLoadMoreEndClick(true)
                    }
                    return
                }
                val dataFinal = ArrayList<NewsGroup>()
                for (newsGroup in data) {
                    if (newsGroup.type == 3) {
                        for (news in newsGroup.data) {
                            dataFinal.add(NewsGroup(newsGroup.type, newsGroup.name, ArrayList<News>().apply { add(news) }))
                        }
                    } else {
                        dataFinal.add(newsGroup)
                    }

                }
                with(mAdapter) {
                    setNewData(dataFinal)
                    setOnLoadMoreListener({
                        Toast.makeText(MyApplication.INSTANCE, "敬请期待~", Toast.LENGTH_SHORT).show()
                        loadMoreEnd()
                    }, mBinding.recyclerView)
                    loadMoreEnd()
                    enableLoadMoreEndClick(true)
                }

            }

            override fun onError(e: Throwable) {
                super.onError(e)
                with(mAdapter) {
                    setOnLoadMoreListener({ loadData(true) }, mBinding.recyclerView)
                    loadMoreFail()
                }
            }

            override fun onFinish() {
                super.onFinish()
                mBinding.swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun loadHeadData() {
        AppRepository.getIndexRepository().getInit().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<IndexResource>() {
            override fun onNext(data: IndexResource) {
                if (data.banners.isNotEmpty()) {
                    mHeadBinding.viewpager.setImageLoader(GlideImageLoader()).setImages(data.banners)
                            .setOnBannerListener {
                                activity?.let { it1 -> data.banners[it].url?.let { it2 -> WebActivity.goWeb(it1, it2) } }
                            }.start()
                } else {
                    mAdapter.removeAllHeaderView()
                }



                if (data.popups.isNotEmpty()) {
                    data.popups.toTypedArray().filter { it.isEffectived() }[0].apply {
                        val indexCommon = this
                        Glide.with(activity!!).load(indexCommon.image).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).listener(object : RequestListener<Drawable> {
                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                resource?.let {
                                    HomePopUpDialog().apply {
                                        val bitmap = (resource as BitmapDrawable).bitmap
                                        arguments = Bundle().apply {
                                            putParcelable("indexCommon", indexCommon)
                                            putInt("height", bitmap.height)
                                            putInt("width", bitmap.width)
                                        }
                                        show(this@NewsFragment.childFragmentManager, "homepopupdialog")
                                    }
                                }
                                return false
                            }

                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                return false
                            }

                        }).preload()
                    }
                }

                if (data.starts.isNotEmpty()) {
                    LitePal.deleteAll(IndexCommon::class.java)
                    for (start in data.starts.toTypedArray().filter { it.isEffectived() }) {
                        start.save()
                        Glide.with(activity!!).load(start.image).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).preload()
                    }
                }
                (activity as MainActivity).setQuits(data.quits)


            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mAdapter.removeAllHeaderView()

            }

        })
    }
}