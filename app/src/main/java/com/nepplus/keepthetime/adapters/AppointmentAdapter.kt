package com.nepplus.keepthetime.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.nepplus.keepthetime.R

import org.json.JSONObject
import java.text.SimpleDateFormat

class AppointmentAdapter(
    val mContext:Context,
    resId: Int,
    val mList: List<AppointmentAdapter>
    ) : ArrayAdapter<AppointmentAdapter>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        if (row == null) {
            row = mInflater.inflate(R.layout.appointment_list_item, null)
        }
        row!!

        val data = mList[position]



        return row
    }

}











