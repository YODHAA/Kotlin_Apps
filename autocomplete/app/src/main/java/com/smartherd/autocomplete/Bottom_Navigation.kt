package com.smartherd.autocomplete

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.bottom_navigation.*

class Bottom_Navigation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_navigation)
//        setContentView(R.menu.bottom_navigation_menu)

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.attach_file -> {
                    text_view.text = "Attach File Clicked."
                    true
                }
                R.id.delete -> {
                    text_view.text = "Delete Clicked."
                    true
                }
                R.id.insert_chart -> {
                    text_view.text = "Insert Chart Clicked."
                    true
                }
                R.id.insert_link -> {
                    text_view.text = "Insert Link Clicked."
                    true
                }
                R.id.backup -> {
                    text_view.text = "Backup Clicked."
                    true
                }
                else -> false
            }
        }


        // Set a navigation item re selected listener for bottom navigation view
        bottom_navigation_view.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.attach_file -> text_view.text = "Reselected Attach File."
                R.id.delete -> text_view.text = "Reselected Delete."
                R.id.insert_chart -> text_view.text = "Reselected Insert Chart."
                R.id.insert_link -> text_view.text = "Reselected Insert Link."
                R.id.backup -> text_view.text = "Reselected Backup."
            }
        }


        // Set a click listener for root layout
        root_layout.setOnClickListener {
            // Programmatically select bottom navigation bar menu item
            bottom_navigation_view.menu.getItem(3).isChecked = true
            text_view.text = "Programmatically Selected  Insert Link."
        }


        // Set the bottom navigation view/bar background color
        bottom_navigation_view.setBackgroundColor(Color.WHITE)
    }
}