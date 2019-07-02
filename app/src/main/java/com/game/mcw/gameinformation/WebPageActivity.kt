package com.game.mcw.gameinformation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.Log
import android.webkit.*
import com.game.mcw.gameinformation.databinding.ActivityWebpageBinding
import com.game.mcw.gameinformation.modle.MyCookie
import org.litepal.LitePal

class WebPageActivity : BaseActivity<ActivityWebpageBinding>() {
    private lateinit var mTitle: String
    private lateinit var mUrl: String
    private var type: Int = 0


    override fun getLayoutId(): Int {
        return R.layout.activity_webpage
    }

    companion object {
        fun goWeb(context: Context, url: String, title: String? = "小游戏") {
            val intent = Intent(context, WebPageActivity::class.java).apply {
                putExtra("web_url", url)
                putExtra("web_title", title)
            }
            context.startActivity(intent)
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.llRoot.setPadding(0, getStatusbarHeight(), 0, 0)
        mTitle = intent.getStringExtra("web_title")
        mUrl = intent.getStringExtra("web_url")

        type = if (mTitle.contains("六")) 1 else 2
        initToolBar()
        val myCookie = LitePal.where("type = ? and url = ?", type.toString(), mUrl).findFirst(MyCookie::class.java)
        if (myCookie != null) {
            Log.e("aaa", "${myCookie.type}-->${myCookie.cookie}")
            for (s in myCookie.cookie.split(";")) {
                CookieManager.getInstance().setCookie(mUrl, s)
            }
        }
        initWebView()


    }

    fun initWebView() {

        with(mBinding.webview) {
            with(settings) {
                setAppCacheEnabled(true)
//                userAgentString = "${userAgentString}asd"
                setAppCachePath(filesDir.absolutePath + "/cachew")
                javaScriptEnabled = true
            }

            webChromeClient = object : WebChromeClient() {

            }
            webViewClient = object : WebViewClient() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    val url = request!!.url.toString()
                    return if (view != null && request != null && (url.startsWith("http"))) {
                        view.loadUrl(url)
                        true
                    } else {
                        super.shouldOverrideUrlLoading(view, request)
                    }

                }

            }

            loadUrl(mUrl)
        }
    }

    private fun initToolBar() {
        setSupportActionBar(mBinding.includeToolbar.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        mBinding.includeToolbar.toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        mBinding.includeToolbar.toolbar.setNavigationOnClickListener {
            finish()
        }
        mBinding.includeToolbar.toolbarTitle.text = mTitle
    }

    override fun onDestroy() {
        val cookieStr = CookieManager.getInstance().getCookie(mUrl)
        if (type != 0 && !TextUtils.isEmpty(cookieStr)) {
            MyCookie(type, mUrl, cookieStr).saveOrUpdate("type = ? and url = ?", type.toString(), mUrl)
        }
        CookieManager.getInstance().removeAllCookies {}
        super.onDestroy()
    }
}