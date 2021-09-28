package com.nepplus.keepthetime.datas

class DataResponse(
//    로그인 성공시 파싱용 변수.
    var user: UserData,
    var token: String,
//    이 밑으로는 약속 목록파싱용 변수.
    var appointments: List<AppointmentData>,
    //    장소 목록
    var places: List<PlaceData>
) {
}