package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.game.mcw.gameinformation.databinding.FragmentGameBinding
import com.game.mcw.gameinformation.databinding.FragmentHomeBinding

class GameFragment : BaseFragment() {
    private lateinit var mBinding: FragmentGameBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.videoPlayerTv.setVideoPlayerVmWithNoTitle()

    }
}