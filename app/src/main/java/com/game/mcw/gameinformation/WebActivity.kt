package com.game.mcw.gameinformation

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.game.mcw.gameinformation.adapter.HomeFragmentPagerAdapter
import com.game.mcw.gameinformation.databinding.ActivityMainBinding
import com.game.mcw.gameinformation.databinding.ActivityWebBinding
import com.just.agentweb.AgentWeb
import java.util.*

class WebActivity : BaseActivity() {
    companion object {
        fun goWeb(context: Context, url: String) {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("web_url", url)
            context.startActivity(intent)
        }
    }

    private lateinit var mBinding: ActivityWebBinding
    private lateinit var agentWeb: AgentWeb


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web)
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mBinding.flWebRoot, FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(intent.getStringExtra("web_url"))

    }

    override fun onPause() {
        agentWeb.webLifeCycle.onPause()
        super.onPause()

    }

    override fun onResume() {
        agentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        agentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }
}