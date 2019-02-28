package com.game.mcw.gameinformation

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.LinearLayout
import com.game.mcw.gameinformation.adapter.HomeFragmentPagerAdapter
import com.game.mcw.gameinformation.databinding.FragmentHomeBinding
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import java.util.*

class HomeRootFragment : BaseFragment() {
    private lateinit var mBinding: FragmentHomeBinding
    private var mFragments: MutableList<Fragment> = ArrayList()
    private lateinit var onGlobalFocusChangeListener: OnGlobalLayoutListener
    private var rootLocationY: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return mBinding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragments.add(NewsFragment())
        mFragments.add(HomeChild2Fragment())
//        mFragments.add(GameFragment())
        mBinding.vpFragment.offscreenPageLimit = mFragments.size
        mBinding.vpFragment.adapter = HomeFragmentPagerAdapter(childFragmentManager, mFragments)


        val barTop = mBinding.tabTop as TabLayout
        barTop.setBackgroundResource(R.drawable.tab_background)
        barTop.setupWithViewPager(mBinding.vpFragment)
        activity?.let { barTop.setTabTextColors(Color.BLACK, ContextCompat.getColor(it, R.color.colorPrimary)) }
        activity?.let { barTop.setSelectedTabIndicatorColor(ContextCompat.getColor(it, R.color.colorPrimary)) }
        activity?.let { barTop.setSelectedTabIndicator(ColorDrawable(ContextCompat.getColor(it, R.color.colorPrimary))) }
        val linearLayout = barTop.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        linearLayout.dividerDrawable = activity?.let { ContextCompat.getDrawable(it, R.drawable.tab_divider) }
        linearLayout.dividerPadding = QMUIDisplayHelper.dp2px(activity, 8)


        val barTopWithBar = mBinding.tabTopBar as TabLayout
        barTopWithBar.setupWithViewPager(mBinding.vpFragment)
        barTopWithBar.setTabTextColors(Color.WHITE, Color.WHITE)
        barTopWithBar.setSelectedTabIndicatorColor(Color.WHITE)
        barTopWithBar.setSelectedTabIndicator(ColorDrawable(Color.WHITE))

        barTopWithBar.visibility = View.GONE
        mBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, p1 ->
            barTopWithBar.visibility = if (barTop.height + p1 == 0) View.VISIBLE else View.GONE
            mBinding.toolbar.toolbarTitle.visibility = if (barTop.height + p1 == 0) View.GONE else View.VISIBLE
        })

    }
}