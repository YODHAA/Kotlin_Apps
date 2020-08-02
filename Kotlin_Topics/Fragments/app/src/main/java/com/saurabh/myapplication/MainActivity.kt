package com.saurabh.myapplication

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.saurabh.myapplication.fragments.FragmentOne
import com.saurabh.myapplication.fragments.FragmentTwo
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var isFragmentOneLoader = true
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar()
        showFragmentOne()
        btn_change.contentDescription = "Button Fragment Change"
        btn_change.setOnClickListener {
            if (isFragmentOneLoader) showFragmentOne()
            else showFragmentTwo()
        }
    }

    private fun setToolBar() {
        toolbar?.setTitle("Fragment Toolbar")
        toolbar?.setSubtitle("Sub-Fragment Heading")
        toolbar?.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_home_black_24dp)
        toolbar?.logo = ContextCompat.getDrawable(this, R.drawable.ic_perm_identity_black_24dp)
        toolbar?.setTitleMargin(10, 10, 10, 10)
        val mActionBar: ActionBar? = supportActionBar
        mActionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        toolbar?.setNavigationOnClickListener {
            Toast.makeText(applicationContext, "Navigation icon was clicked", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun showFragmentOne() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentOne()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        isFragmentOneLoader = false
    }

    private fun showFragmentTwo() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentTwo()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        isFragmentOneLoader = true
    }

    // Menu Related code
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // menuInflater.inflate(R.menu.ellipses_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.moreVertical -> {
                Toast.makeText(applicationContext, "click on setting", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
