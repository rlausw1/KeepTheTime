package com.nepplus.keepthetime

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "38dc8889b0b30e00d95728984da4a28f")
    }
}