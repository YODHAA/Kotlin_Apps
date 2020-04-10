package com.example.hobbies_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShowTest.setOnClickListener {
            Log.i("Main Activity","Button was Clicked")
            Toast.makeText(this,"Button was Clicked",Toast.LENGTH_LONG).show()
        }

    }
}
