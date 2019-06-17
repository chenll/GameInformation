package com.game.mcw.gameinformation.adapter

import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.R
import com.game.mcw.gameinformation.adapter.base.BaseMVMultiItemAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.binding.GamePresenter
import com.game.mcw.gameinformation.databinding.ItemNews3Binding
import com.game.mcw.gameinformation.databinding.ItemNews4Binding
import com.game.mcw.gameinformation.databinding.TestItem1Binding
import com.game.mcw.gameinformation.modle.News
import com.game.mcw.gameinformation.modle.NewsGroup


class NewsAdapter : BaseMVMultiItemAdapter<NewsGroup, MVViewHolder>() {

    val mGamePresenter = GamePresenter()

    init {
        addItemType(1, R.layout.item_news_3)
        addItemType(2, R.layout.item_news_4)
        addItemType(3, R.layout.test_item_1)
        addItemType(4, R.layout.test_item_1_1)
//        addItemType(1, R.layout.test_item_1)
//        addItemType(2, R.layout.test_item_1_1)
//        addItemType(3, R.layout.item_news_3)
//        addItemType(4, R.layout.item_news_4)
    }

    override fun convert(helper: MVViewHolder, item: NewsGroup) {

        when (helper.itemViewType) {
            1 -> {
                convert3(helper, item)
            }
            2 -> {
                convert4(helper, item)
            }
            3 -> {
                convert1(helper, item.data[0])
            }
            4 -> {
                convert2(helper, item.data[0])
            }
        }


    }


    private fun convert1(helper: MVViewHolder, item: News) {
        val binding = helper.getBinding() as TestItem1Binding
        binding.item = item
        binding.presenter = mGamePresenter
        binding.executePendingBindings()
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
        (binding.recyclerView.adapter as NewsSimply3Adapter).setNewData(item.data)
    }

    private fun convert4(helper: MVViewHolder, item: NewsGroup) {
        val binding = helper.getBinding() as ItemNews4Binding
        binding.setVariable(BR.item, item)
        if (binding.recyclerView.adapter == null) {
            NewsSimply4Adapter(R.layout.item_item_news_2).bindToRecyclerView(binding.recyclerView)
        }
        (binding.recyclerView.adapter as NewsSimply4Adapter).setNewData(item.data)
    }

}