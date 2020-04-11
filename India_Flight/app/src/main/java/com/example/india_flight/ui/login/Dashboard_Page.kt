package com.example.india_flight.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.india_flight.R
import kotlinx.android.synthetic.main.dashboard_home.*

class Dashboard_Page :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_home)

        val msg:String="India Flight Dashboard"
        etShowMessage.text=msg


    }

}