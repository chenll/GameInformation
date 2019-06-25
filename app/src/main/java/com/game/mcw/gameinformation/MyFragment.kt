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
//        if (MyUserManager.instance.userBean != null) {
//            Toast.makeText(activity, "已登录${MyUserManager.instance.userBean!!.userId}", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(activity, "未登录", Toast.LENGTH_SHORT).show()
//        }
        initViews()


    }


    private fun initViews() {
        MyUserManager.instance.userBean?.let { GlideUtil.loadCircleHeadPic(it.avatar, mBinding.ivHead) }
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mBinding.rvMyHead.layoutManager = layoutManager
        mCardAdapter = MyCardAdapter(R.layout.item_my_card).apply {
            addData(Card(1, "优惠券", R.mipmap.my_card_icon_1, intArrayOf(Color.parseColor("#9D55FE"), Color.parseColor("#E15EFB"))))
            addData(Card(0, "积分", R.mipmap.my_card_icon_2, intArrayOf(Color.parseColor("#FE425F"), Color.parseColor("#FE7224"))))
            addData(Card(1, "零钱", R.mipmap.my_card_icon_3, intArrayOf(Color.parseColor("#2CD3ED"), Color.parseColor("#25E8E5"))))
            addData(Card(6, "金钻", R.mipmap.my_card_icon_4, intArrayOf(Color.parseColor("#FF8268"), Color.parseColor("#FF9D1A"))))
            bindToRecyclerView(mBinding.rvMyHead)

        }


        val layoutManagerBody = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.rvMyBody.layoutManager = layoutManagerBody
        mAdapter = MyAdapter(R.layout.item_my).apply {
            addData(Card(111, "每日任务", R.mipmap.my_icon_1, intArrayOf(Color.parseColor("#A55DFD"), Color.parseColor("#E463FB"))))
            addData(Card(222, "积分商城", R.mipmap.my_icon_2, intArrayOf(Color.parseColor("#FE4A65"), Color.parseColor("#EF7B3F"))))
            addData(Card(333, "VIP特权", R.mipmap.my_icon_3, intArrayOf(Color.parseColor("#36D5F2"), Color.parseColor("#2EE8EB"))))
            addData(Card(444, "在线客服", R.mipmap.my_icon_4, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
            addData(Card(444, "收入明细", R.mipmap.my_icon_5, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
            addData(Card(444, "隐私政策", R.mipmap.my_icon_6, intArrayOf(Color.parseColor("#FC8970"), Color.parseColor("#F6A524"))))
            bindToRecyclerView(mBinding.rvMyBody)
            setOnItemClickListener { _, _, position ->
                when (position) {
                    0 -> startActivity(TaskActivity::class.java)
                    1 -> startActivity(CommodityListActivity::class.java)
                }
            }
        }

    }

    fun click(v: View) {
        startActivity(if (MyUserManager.instance.userBean == null) LoginActivity::class.java else UserInfoActivity::class.java)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun Event(userChangeEvent: UserChangeEvent) {
        mBinding.user = MyUserManager.instance.userBean
        GlideUtil.loadCircleHeadPic(if (MyUserManager.instance.userBean == null) "" else MyUserManager.instance.userBean!!.avatar, mBinding.ivHead)
    }

}