package com.nepplus.keepthetime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.nepplus.keepthetime.utils.ContextUtil
import com.nepplus.keepthetime.utils.GlobalData

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed({

            val myIntent : Intent
            if(ContextUtil.getToken(mContext) != "") {

//                GlobalData.loginUser = ?

                myIntent = Intent(mContext, MainActivity::class.java)
            }
            else {
                myIntent = Intent(mContext, LoginActivity::class.java)
            }
            startActivity(myIntent)

        }, 2500)


    }
}