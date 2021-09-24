package com.nepplus.keepthetime.datas

class DataResponse(
//    로그인 성공시 파싱용 변수
    var token: String,
    var user: UserData,
//    이 밑으로는 약속목록파싱용 변수
    var appointments: List<AppointmentData>
) {
}