package com.game.mcw.gameinformation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.game.mcw.gameinformation.databinding.ActivityTestBinding

class TestActivity : BaseActivity<ActivityTestBinding>() {
    companion object {
        val INTEGER_THREAD_LOCAL: ThreadLocal<Int> = ThreadLocal()

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Looper.getMainLooper().
        INTEGER_THREAD_LOCAL.set(1)
        Log.e("aaa", "主线程：${Thread.currentThread().id}=${INTEGER_THREAD_LOCAL.get()}")
        Thread(Runnable {
            //            Log.e("qdx", "step 0 ")
            Looper.prepare()
            Handler {
                Log.e("aaa", "===========")
                false
            }
//            Log.e("qdx", "step 1 ")
//            Log.e("aaa", "子线程：${Thread.currentThread().id}=${INTEGER_THREAD_LOCAL.get()}")
            Toast.makeText(this@TestActivity, "run on thread", Toast.LENGTH_SHORT).show()
            Looper.loop()
//            Log.e("qdx", "step 2")

        }).start()

    }


}