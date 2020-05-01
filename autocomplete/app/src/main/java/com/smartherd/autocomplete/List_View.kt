package com.smartherd.autocomplete

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.list_view.*

class List_View : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)

        val adapter = ListColorBaseAdapter()
        list_view.adapter = adapter
    }
}
