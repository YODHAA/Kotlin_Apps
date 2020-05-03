package com.smartherd.autocomplete.editText

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smartherd.autocomplete.R

class Edit_Text : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edittext_layout)

        // finding the button
        val showButton = findViewById<Button>(R.id.showInput)

        // finding the edit text
        val editText = findViewById<EditText>(R.id.editText)

        // Setting On Click Listener
        showButton.setOnClickListener {

            // Getting the user input
            val text = editText.text

            // Showing the user input
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}