package com.example.hobbies_layout

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // attach layout made to this activity
        setContentView(R.layout.activity_second)

        //catch the sent message here
         val bundle:Bundle? = intent.extras
        val msg=bundle!!.getString("user_message")

        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        txvUserMessage.text=msg
    }


}