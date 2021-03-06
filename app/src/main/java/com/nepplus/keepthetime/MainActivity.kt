package com.nepplus.keepthetime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nepplus.keepthetime.adapters.AppointmentAdapter
import com.nepplus.keepthetime.adapters.AppointmentRecyclerAdapter
import com.nepplus.keepthetime.databinding.ActivityMainBinding
import com.nepplus.keepthetime.datas.AppointmentData
import com.nepplus.keepthetime.datas.BasicResponse
import com.nepplus.keepthetime.utils.GlobalData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mAppointmentList = ArrayList<AppointmentData>()
//    lateinit var mAdapter : AppointmentAdapter
    lateinit var mRecyclerAdapter : AppointmentRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun onResume() {
        super.onResume()
        getAppointmentListFromServer()
    }

    override fun setupEvents() {

        binding.addAppoinmentBtn.setOnClickListener {
            val myIntent = Intent(mContext, EditAppointmentActivity::class.java)
            startActivity(myIntent)
        }

        profileImg.setOnClickListener {
            val myIntent = Intent(mContext, MySettingActivity::class.java)
            startActivity(myIntent)
        }


    }

    override fun setValues() {
        Toast.makeText(mContext, "${GlobalData.loginUser!!.nickName}님 환영합니다!", Toast.LENGTH_SHORT).show()

//        getAppointmentListFromServer()

//        mAdapter = AppointmentAdapter(mContext, R.layout.appointment_list_item, mAppointmentList)
//        binding.appointmentListView.adapter = mAdapter

        mRecyclerAdapter = AppointmentRecyclerAdapter(mContext, mAppointmentList)
        binding.addAppoinmentRecyclerView.adapter = mRecyclerAdapter

        binding.addAppoinmentRecyclerView.layoutManager = LinearLayoutManager(mContext)

//        상속받은, 액션바에 있는 프로필버튼 보여주기.
        profileImg.visibility = View.VISIBLE

//        메인화면의 화면 제목 변경
        titleTxt.text = "메인 화면"

    }
    fun getAppointmentListFromServer() {
        apiService.getRequestAppointmentList().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                val basicResponse = response.body()!!

                mAppointmentList.clear()

//                약속목록변수에 => 서버가 알려준 약속목록을 전부 추가.
                mAppointmentList.addAll( basicResponse.data.appointments )

//                어댑터 새로고침
                mRecyclerAdapter.notifyDataSetChanged()   }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
            }
        })
    }
}