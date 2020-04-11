package com.example.hobbies_layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // attach layout made to this activity
        setContentView(R.layout.activity_second)
    }


}