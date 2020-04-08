package com.example.startup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShowToast.setOnClickListener {
            Log.i("MainActivity","Button was clicked!!!")

            Toast.makeText(this,"Button was Clicked sasasadasd !",Toast.LENGTH_LONG).show()
        }

        btnSendMsgToNextActivity.setOnClickListener {
            val message:String = etUserMessage.text.toString()
            Toast.makeText(this,"Button For Send Message To Next Activity was Clicked !!!",Toast.LENGTH_LONG).show()

            // Explicit Intent
            val intent = Intent(this,SecondActivity::class.java)

            intent.putExtra("user_message",message)

            startActivity(intent)

        }

        btnShareToAnotherApps.setOnClickListener {
            val message: String =etUserMessage.text.toString()
            val intent=Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type="text/plain"

            startActivity(Intent.createChooser(intent,"Share To: "))
        }

        btnRecyclerViewDemo.setOnClickListener {
            val intent= Intent(this,HobbiesActivity::class.java)
        }
    }
}
