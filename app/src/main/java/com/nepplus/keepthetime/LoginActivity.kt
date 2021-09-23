package com.nepplus.keepthetime

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.facebook.CallbackManager
import com.nepplus.keepthetime.databinding.ActivityLoginBinding
import java.security.MessageDigest
import com.facebook.FacebookException

import com.facebook.login.LoginResult

import com.facebook.FacebookCallback


import android.view.View

import com.facebook.login.widget.LoginButton









class LoginActivity : BaseActivity() {

    lateinit var callbackManager : CallbackManager

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupEvents()
        setValues()


    }

    override fun setupEvents() {

//        페북 로그인 버튼 클릭 이벤트
        callbackManager = CallbackManager.Factory.create();

        binding.loginButton.setReadPermissions("email")

        binding.loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                // App code

                Log.d("확인용", loginResult.toString())
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })

    }





        override fun setValues() {

//        카톡으로 받은 코드 복붙 => 키 해쉬값 추출
            val info = packageManager.getPackageInfo(
                "com.nepplus.keepthetime",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


    }
