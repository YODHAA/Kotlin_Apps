package com.smartherd.autocomplete

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class image_view : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_view)

        val button = findViewById<Button>(R.id.button)
        val imageView = ImageView(this)
        val layout = findViewById<RelativeLayout>(R.id.layout)
        imageView.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageView.x = 580F // setting margin from left
        imageView.y = 580F // setting margin from top

        button.setOnClickListener {
            imageView.setImageResource(R.drawable.login)
            layout?.addView(imageView)
        }

    }
}