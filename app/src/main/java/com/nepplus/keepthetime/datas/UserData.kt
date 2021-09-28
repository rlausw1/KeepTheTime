package com.nepplus.keepthetime.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserData(
    var id : Int,
    var provider : String,
    @SerializedName("nick_name")
    var nickName : String,
    var email: String,
    @SerializedName("ready_minute")
    var readyMinute: Int) : Serializable {
}