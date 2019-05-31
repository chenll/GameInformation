package com.game.mcw.gameinformation.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.game.mcw.gameinformation.BR
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.adapter.base.BaseMVMultiItemAdapter
import com.game.mcw.gameinformation.adapter.base.MVViewHolder
import com.game.mcw.gameinformation.databinding.ItemNews3Binding
import com.game.mcw.gameinformation.databinding.ItemNews3BindingImpl
import com.game.mcw.gameinformation.databinding.ItemNews4Binding
import com.game.mcw.gameinformation.modle.News
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class NewsSimplyApaptercopy : BaseMVMultiItemAdapter<News, MVViewHolder>() {

    var options: RequestOptions

    init {
        val roundedCornersTransformation = RoundedCornersTransformation(QMUIDisplayHelper.dp2px(MyApplication.INSTANCE, 5), 0, RoundedCornersTransformation.CornerType.ALL)
        options = RequestOptions().transforms(CenterCrop(), roundedCornersTransformation)
        addItemType(News.type_1, com.game.mcw.gameinformation.R.layout.test_item_1)
        addItemType(News.type_2, com.game.mcw.gameinformation.R.layout.test_item_1_1)
        addItemType(News.type_3, com.game.mcw.gameinformation.R.layout.item_news_3)
        addItemType(News.type_4, com.game.mcw.gameinformation.R.layout.item_news_4)
    }

    override fun convert(helper: MVViewHolder, item: News) {

        when (helper.itemViewType) {
            News.type_1 -> {
                convert1(helper, item)
            }
            News.type_2 -> {
                convert2(helper, item)
            }
            News.type_3 -> {
                convert3(helper, item)
            }
            News.type_4 -> {
                convert4(helper, item)
            }
        }


    }


    private fun convert1(helper: MVViewHolder, item: News) {
        helper.getBinding().setVariable(BR.item, item)
        helper.getBinding().executePendingBindings()
        Glide.with(mContext).load(item.img).apply(options).into(helper.getView(com.game.mcw.gameinformation.R.id.iv_pic))
    }


    private fun convert2(helper: MVViewHolder, item: News) {
        helper.getBinding().setVariable(BR.item, item)
        helper.getBinding().executePendingBindings()
        Glide.with(mContext).load(item.img).apply(options).into(helper.getView(com.game.mcw.gameinformation.R.id.iv_pic))
    }


    private fun convert3(helper: MVViewHolder, item: News) {
        val binding = helper.getBinding() as ItemNews3Binding
        if (binding.recyclerView.adapter == null) {
            initconvert3RecyclerView(binding)
        }

    }

    private fun convert4(helper: MVViewHolder, item: News) {}


    private fun initconvert3RecyclerView(binding: ItemNews3Binding) {
        val layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager

    }

    private fun initconvert4RecyclerView(binding: ItemNews4Binding) {
        val layoutManager = GridLayoutManager(mContext, LinearLayoutManager.VERTICAL, 2, false)
        binding.recyclerView.layoutManager = layoutManager

    }
}