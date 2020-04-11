package com.example.hobbies_layout

import android.content.Intent
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

        btnSendMessageToNextActivity.setOnClickListener {
            Log.i("Main Activity","Next Activity Button was Clicked")
            Toast.makeText(this,"Second Button was Clicked",Toast.LENGTH_LONG).show()
            // taking and extract the input from the etUserMessage to get the message from layout Activity
            val message:String = etUserMessage.text.toString()
            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
            // transition to next Activity
            val intent=Intent(this,SecondActivity::class.java)
            // pass msg to next Intent
            
            startActivity(intent)
        }

    }
}
