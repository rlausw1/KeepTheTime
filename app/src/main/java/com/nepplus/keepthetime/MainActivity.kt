package com.nepplus.keepthetime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nepplus.keepthetime.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.addAppoinmentBtn.setOnClickListener {
            val myIntent = Intent(mContext, EditAppointmentActivity::class.java)
            startActivity(myIntent)
        }



    }

    override fun setValues() {

    }
}