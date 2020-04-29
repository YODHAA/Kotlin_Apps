package com.smartherd.autocomplete

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.grid_view.*

class Grid_View : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_view)

        val adapter = ColorBaseAdapter()

        // Set the grid view adapter
        grid_view.adapter = adapter

        // Configure the grid view
        grid_view.numColumns = 2
        grid_view.horizontalSpacing = 15
        grid_view.verticalSpacing = 15
        grid_view.stretchMode = GridView.STRETCH_COLUMN_WIDTH

    }
}