package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.game.mcw.gameinformation.adapter.MyAdapter
import com.game.mcw.gameinformation.adapter.MyCardAdapter
import com.game.mcw.gameinformation.databinding.FragmentMyBinding
import com.game.mcw.gameinformation.event.UserChangeEvent
import com.game.mcw.gameinformation.manager.MyUserManager
import com.game.mcw.gameinformation.modle.Card
import com.game.mcw.gameinformation.utils.GlideUtil
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MyFragment : BaseFragment() {

    private lateinit var mBinding: FragmentMyBinding
    private lateinit var mCardAdapter: MyCardAdapter
    private lateinit var mAdapter: MyAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false)
        mBinding.fragment = this
        mBinding.user = MyUserManager.instance.userBean
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEventBus()
        mBinding.user = MyUserManager.instance.userBean
        if (MyUserManager.instance.userBean != null) {
            Toast.makeText(activity, "已登录${MyUserManager.instance.userBean!!.userId}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "未登录", Toast.LENGTH_SHORT).show()
        }
        initViews()


    }


    private fun initViews() {

        GlideUtil.loadCircleHeadPic("", mBinding.ivHead)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mBinding.rvMyHead.layoutManager = layoutManager
        mCardAdapter = MyCardAdapter(R.layout.item_my_card)
        mCardAdapter.addData(Card(111, "优惠券", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#A55DFD"), Color.parseColor("#E463FB"))))
        mCardAdapter.addData(Card(222, "积分", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FE4A65"), Color.parseColor("#EF7B3F"))))
        mCardAdapter.addData(Card(333, "零钱", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#36D5F2"), Color.parseColor("#2EE8EB"))))
        mCardAdapter.addData(Card(444, "金钻", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
        mCardAdapter.bindToRecyclerView(mBinding.rvMyHead)


        val layoutManagerBody = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.rvMyBody.layoutManager = layoutManagerBody
        mAdapter = MyAdapter(R.layout.item_my)
        mAdapter.addData(Card(111, "每日任务", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#A55DFD"), Color.parseColor("#E463FB"))))
        mAdapter.addData(Card(222, "积分商城", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FE4A65"), Color.parseColor("#EF7B3F"))))
        mAdapter.addData(Card(333, "VIP特权", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#36D5F2"), Color.parseColor("#2EE8EB"))))
        mAdapter.addData(Card(444, "在线客服", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
        mAdapter.addData(Card(444, "收入明细", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
        mAdapter.addData(Card(444, "隐私政策", R.mipmap.ic_launcher, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
        mAdapter.bindToRecyclerView(mBinding.rvMyBody)
    }

    fun click(v: View) {
        startActivity(if (MyUserManager.instance.userBean == null) LoginActivity::class.java else UserInfoActivity::class.java)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun Event(userChangeEvent: UserChangeEvent) {
        mBinding.user = MyUserManager.instance.userBean

    }

}