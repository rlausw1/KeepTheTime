package com.nepplus.keepthetime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.nepplus.keepthetime.databinding.ActivityViewMyPlaceListBinding

class ViewMyPlaceListActivity : BaseActivity() {

    lateinit var binding: ActivityViewMyPlaceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_my_place_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        addBtn.setOnClickListener {
            val myIntent = Intent(mContext, EditMyPlaceActivity::class.java)
            startActivity(myIntent)

        }

    }

    override fun setValues() {
        titleTxt.text = "내가 자주 쓰는 출발장소들"
        addBtn.visibility = View.VISIBLE

    }
}