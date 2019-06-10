package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.game.mcw.gameinformation.adapter.MyApapter
import com.game.mcw.gameinformation.adapter.MyCardApapter
import com.game.mcw.gameinformation.adapter.NewsSimply3Apapter
import com.game.mcw.gameinformation.databinding.FragmentMyBinding
import com.game.mcw.gameinformation.modle.Card
import com.game.mcw.gameinformation.utils.GlideUtil
import kotlinx.android.synthetic.main.fragment_my.view.*

class MyFragment : BaseFragment() {

    private lateinit var mBinding: FragmentMyBinding
    private lateinit var mCardAdapter: MyCardApapter
    private lateinit var mAdapter: MyApapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false)
        mBinding.fragment = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlideUtil.loadCircleHeadPic("", mBinding.ivHead)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mBinding.rvMyHead.layoutManager = layoutManager
        mCardAdapter = MyCardApapter(R.layout.item_my_card)
        mCardAdapter.addData(Card(111, "优惠券", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#A55DFD"), Color.parseColor("#E463FB"))))
        mCardAdapter.addData(Card(222, "积分", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FE4A65"), Color.parseColor("#EF7B3F"))))
        mCardAdapter.addData(Card(333, "零钱", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#36D5F2"), Color.parseColor("#2EE8EB"))))
        mCardAdapter.addData(Card(444, "金钻", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
        mCardAdapter.bindToRecyclerView(mBinding.rvMyHead)


        val layoutManagerBody = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.rvMyBody.layoutManager = layoutManagerBody
        mAdapter = MyApapter(R.layout.item_my)
        mAdapter.addData(Card(111, "每日任务", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#A55DFD"), Color.parseColor("#E463FB"))))
        mAdapter.addData(Card(222, "积分商城", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FE4A65"), Color.parseColor("#EF7B3F"))))
        mAdapter.addData(Card(333, "VIP特权", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#36D5F2"), Color.parseColor("#2EE8EB"))))
        mAdapter.addData(Card(444, "在线客服", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
        mAdapter.addData(Card(444, "收入明细", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
        mAdapter.addData(Card(444, "隐私政策", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
        mAdapter.bindToRecyclerView(mBinding.rvMyBody)
    }

    fun click(v: View) {
        if (v.id == R.id.iv_head) {
            startActivity(UserInfoActivity::class.java)
        } else {
            startActivity(LoginActivity::class.java)
        }
    }

}