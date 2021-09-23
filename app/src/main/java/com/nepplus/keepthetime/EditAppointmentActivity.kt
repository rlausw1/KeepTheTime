package com.nepplus.keepthetime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nepplus.keepthetime.databinding.ActivityEditAppointmentBinding

class EditAppointmentActivity : BaseActivity() {
    lateinit var binding: ActivityEditAppointmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        //        확인버튼이 눌리면?

        binding.okBtn.setOnClickListener {

//            입력한 값들 받아오기
//            1. 일정 제목
            val inputTitle = binding.titleEdt.text.toString()

//            2. 약속 일시? -> "2021-09-13 11:11" String 변환까지.

//            3. 약속 장소?
//            - 장소 이름
            val inputPlaceName = binding.placeSearchEdt.text.toString()

//            - 장소 위도/경도?

        }
    }


    override fun setValues() {
    }
}