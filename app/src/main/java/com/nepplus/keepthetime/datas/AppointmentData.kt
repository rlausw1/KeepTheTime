package com.nepplus.keepthetime.datas

import com.google.gson.annotations.SerializedName

class AppointmentData (
    var id: Int,
    @SerializedName("user_id")
    var userId: Int,
    var title: String,
    var datetime: String, // 일단 String -> 파싱 기능 수정 => Date형태로 받자. (Calendar와 엮어서 사용)
    @SerializedName("place")
    var placeName: String,
    var latitude: Double,
    var longitude: Double,
    @SerializedName("created_at")
    var createdAt: String,
    var user: UserData

        ){
}