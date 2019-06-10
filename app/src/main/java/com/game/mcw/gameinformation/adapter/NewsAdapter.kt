package com.game.mcw.gameinformation.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVMultiItemAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemNews3Binding
import com.game.mcw.gameinformation.databinding.ItemNews4Binding
import com.game.mcw.gameinformation.modle.News
import com.game.mcw.gameinformation.modle.NewsGroup
import com.game.mcw.gameinformation.utils.GlideUtil


class NewsAdapter : BaseMVMultiItemAdapter<NewsGroup, MVViewHolder>() {


    init {
        addItemType(1, R.layout.test_item_1)
        addItemType(2, R.layout.test_item_1_1)
        addItemType(3, R.layout.item_news_3)
        addItemType(4, R.layout.item_news_4)
    }

    override fun convert(helper: MVViewHolder, item: NewsGroup) {

        when (helper.itemViewType) {
            1 -> {
                convert1(helper, item.news[0])
            }
            2 -> {
                convert2(helper, item.news[0])
            }
            3 -> {
                convert3(helper, item)
            }
            4 -> {
                convert4(helper, item)
            }
        }


    }


    private fun convert1(helper: MVViewHolder, item: News) {
        helper.getBinding().setVariable(BR.item, item)
        helper.getBinding().executePendingBindings()
        GlideUtil.loadBorderRadiusGameIcon(item.img,helper.getView(R.id.iv_pic))
    }


    private fun convert2(helper: MVViewHolder, item: News) {
        helper.getBinding().setVariable(BR.item, item)
        helper.getBinding().executePendingBindings()
        GlideUtil.loadBorderRadiusGameIcon(item.img,helper.getView(R.id.iv_pic))
    }


    private fun convert3(helper: MVViewHolder, item: NewsGroup) {
        val binding = helper.getBinding() as ItemNews3Binding
        binding.setVariable(BR.item, item)
        if (binding.recyclerView.layoutManager == null) {
            initConvert3RecyclerView(binding)
        }
        (binding.recyclerView.adapter as NewsSimply3Adapter).setNewData(item.news)
    }

    private fun convert4(helper: MVViewHolder, item: NewsGroup) {
        val binding = helper.getBinding() as ItemNews4Binding
        binding.setVariable(BR.item, item)
        if (binding.recyclerView.layoutManager == null) {
            initConvert4RecyclerView(binding)
        }
        (binding.recyclerView.adapter as NewsSimply4Adapter).setNewData(item.news)

    }


    private fun initConvert3RecyclerView(binding: ItemNews3Binding) {
        val layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager
        NewsSimply3Adapter(R.layout.item_item_news_1).bindToRecyclerView(binding.recyclerView)
    }

    private fun initConvert4RecyclerView(binding: ItemNews4Binding) {
        val layoutManager = GridLayoutManager(mContext, 2)
        binding.recyclerView.layoutManager = layoutManager
        NewsSimply4Adapter(R.layout.item_item_news_2).bindToRecyclerView(binding.recyclerView)

    }
}