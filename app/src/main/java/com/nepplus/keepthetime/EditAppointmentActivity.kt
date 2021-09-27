package com.nepplus.keepthetime

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate

import com.naver.maps.map.MapFragment
import com.nepplus.keepthetime.databinding.ActivityEditAppointmentBinding
import com.nepplus.keepthetime.datas.BasicResponse
import com.nepplus.keepthetime.utils.ContextUtil


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {
    lateinit var binding: ActivityEditAppointmentBinding

//    선택한 약속 일시를 저장할 변수
    val mSelectedDateTime = Calendar.getInstance()  // 기본값 : 현재 시간

    //    선택한 약속장소를 저장할 변수.
    var mSelectedLat = 0.0 // Double을 넣을것임.
    var mSelectedLng = 0.0 // Double



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        날짜 선택
        binding.dateTxt.setOnClickListener {
            //            DatePicker 띄우기 -> 입력 완료되면,  연/월/일을 제공해줌.
//            mSelec... 에 연/월/일 저장

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {

//                    선택된 날짜로서 지정.
                    mSelectedDateTime.set(year, month, day)

//                    선택된 날짜로 문구 변경. => 2021. 9. 8 (월) => SimpleDateFormat

                    val sdf = SimpleDateFormat("yyyy. M. d (E)")
                    binding.dateTxt.text = sdf.format( mSelectedDateTime.time )

                }

            }

            val dpd = DatePickerDialog(mContext, dateSetListener,
                mSelectedDateTime.get(Calendar.YEAR),
                mSelectedDateTime.get(Calendar.MONTH),
                mSelectedDateTime.get(Calendar.DAY_OF_MONTH))

            dpd.show()

        }
//        시간 선택
        binding.timeTxt.setOnClickListener {
//            TimePicker 띄우기 -> 입력 완료되면   시/분 제공
//             mSelect... 에 시/분 저장

            val tsl = object  : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {

                    mSelectedDateTime.set(Calendar.HOUR_OF_DAY, hour)
                    mSelectedDateTime.set(Calendar.MINUTE, minute)

//                    오후 6:05  형태로 가공. => SimpleDateFormat
                    val sdf = SimpleDateFormat("a h:mm")
                    binding.timeTxt.text = sdf.format(mSelectedDateTime.time)

                }
            }

            TimePickerDialog(mContext, tsl,
                mSelectedDateTime.get(Calendar.HOUR_OF_DAY),
                mSelectedDateTime.get(Calendar.MINUTE),
                false).show()

        }

        binding.okBtn.setOnClickListener {
//            입력한 값들 받아오기
//            1. 일정 제목
            val inputTitle = binding.titleEdt.text.toString()
//            2. 약속 일시? -> "2021-09-13 11:11" String 변환까지.
//              => 날짜 / 시간중 선택 안한게 있다면? 선택하라고 토스트, 함수 강제 종료. (vaildation)
            if ( binding.dateTxt.text == "일자 설정" ) {
                Toast.makeText(mContext, "일자를 설정하지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.timeTxt.text == "시간 설정") {
                Toast.makeText(mContext, "시간을 설정하지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            여기 코드 실행된다 : 일자 / 시간 모두 설정했다.
//            선택된 약속일시를 -> "yyyy-MM-dd HH:mm" 양식으로 가공. => 최종 서버에 파라미터로 첨부
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val finalDatetime = sdf.format(mSelectedDateTime.time)
            Log.d("서버에보낼 약속일시", finalDatetime)
//            3. 약속 장소?
//            - 장소 이름
            val inputPlaceName = binding.placeSearchEdt.text.toString()
//            - 장소 위도/경도?  (임시 : 학원 좌표 하드코딩)
//            val lat = 37.57794132143432
//            val lng = 127.03353823833795

            //            지도에서 클릭한 좌표로  위경도 첨부.
//            선택 안했다면? 선택해달라고 안내.

            if (mSelectedLat == 0.0 && mSelectedLng == 0.0) {
                Toast.makeText(mContext, "약속 장소를 지도를 클릭해 선택해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            서버에 API 호출
            apiService.postRequestAppointment(
                inputTitle,
                finalDatetime,
                inputPlaceName,
                mSelectedLat, mSelectedLng).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {
                        Toast.makeText(mContext, "약속을 등록했습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                }
            })
        }
    }



    override fun setValues() {

        //        카카오 지도 띄워보기
//
//        val mapView = MapView(mContext)
//
//        binding.mapView.addView(mapView)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.naverMapView) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.naverMapView, it).commit()
            }

        mapFragment.getMapAsync {
            Log.d("지도객체-바로할일", it.toString())

//            학원 좌표를 지도 시작점으로. (예제)

//            it.mapType = NaverMap.MapType.Hybrid

//            좌표를 다루는 변수 - LatLng클래스 활용.
            val neppplusCoord = LatLng(37.57793737795487, 127.03355269913862)

            val cameraUpdate = CameraUpdate.scrollTo(neppplusCoord)
            it.moveCamera(cameraUpdate)

            val uiSettings = it.uiSettings
            uiSettings.isCompassEnabled = true
            uiSettings.isScaleBarEnabled = false

            it.setOnMapClickListener { pointF, latLng ->
                Toast.makeText(
                    mContext,
                    "위도 : ${latLng.latitude},  경도 : ${latLng.longitude}",
                    Toast.LENGTH_SHORT
                ).show()

                mSelectedLat = latLng.latitude
                mSelectedLng = latLng.longitude
            }
        }

    }
}