package com.smartherd.autocomplete.bottombar

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.smartherd.autocomplete.R
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.bottom_app_bar_layout.*


class MainBottomAppBar  : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_app_bar_layout)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         val inflater=menuInflater
         inflater.inflate(R.menu.app_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.menuAbout -> toast("Info is clicked!")
            R.id.menuSettings -> toast("Setting is clicked!")
        }
        return true
    }

    // This is an extension method for easy Toast call
    fun Context.toast(message:CharSequence){
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 325)
        toast.show()
    }


}