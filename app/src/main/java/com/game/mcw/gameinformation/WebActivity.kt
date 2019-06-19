package com.game.mcw.gameinformation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import com.game.mcw.gameinformation.databinding.ActivityWebBinding
import com.just.agentweb.AgentWeb

class WebActivity : BaseActivity<ActivityWebBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }

    companion object {
        fun goWeb(context: Context, url: String) {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("web_url", url)
            context.startActivity(intent)
        }
    }

    private lateinit var agentWeb: AgentWeb


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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