package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVMultiItemAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemNews3Binding
import com.game.mcw.gameinformation.databinding.ItemNews4Binding
import com.game.mcw.gameinformation.modle.News
import com.game.mcw.gameinformation.modle.NewsGroup


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
    }


    private fun convert2(helper: MVViewHolder, item: News) {
        helper.getBinding().setVariable(BR.item, item)
        helper.getBinding().executePendingBindings()
    }


    private fun convert3(helper: MVViewHolder, item: NewsGroup) {
        val binding = helper.getBinding() as ItemNews3Binding
        binding.setVariable(BR.item, item)
        if (binding.recyclerView.adapter == null) {
            NewsSimply3Adapter(R.layout.item_item_news_1).bindToRecyclerView(binding.recyclerView)
        }
        (binding.recyclerView.adapter as NewsSimply3Adapter).setNewData(item.news)
    }

    private fun convert4(helper: MVViewHolder, item: NewsGroup) {
        val binding = helper.getBinding() as ItemNews4Binding
        binding.setVariable(BR.item, item)
        if (binding.recyclerView.adapter == null) {
            NewsSimply4Adapter(R.layout.item_item_news_2).bindToRecyclerView(binding.recyclerView)
        }
        (binding.recyclerView.adapter as NewsSimply4Adapter).setNewData(item.news)
    }

}