package com.game.mcw.gameinformation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.FrameLayout
import com.game.mcw.gameinformation.databinding.ActivityWebBinding
import com.just.agentweb.AgentWeb

class WebActivity : BaseActivity<ActivityWebBinding>() {
    private lateinit var agentWeb: AgentWeb
    private lateinit var title: String


    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }

    companion object {
        fun goWeb(context: Context, url: String, title: String? = "小游戏") {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("web_url", url)
            intent.putExtra("web_title", title)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.llRoot.setPadding(0, getStatusbarHeight(), 0, 0)
        title = intent.getStringExtra("web_title")
        initToolBar()
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mBinding.flWebRoot, FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(intent.getStringExtra("web_url"))

    }

    private fun initToolBar() {
        setSupportActionBar(mBinding.includeToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.includeToolbar.toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        mBinding.includeToolbar.toolbar.setNavigationOnClickListener {
            finish()
        }
        mBinding.includeToolbar.toolbarTitle.text = title
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