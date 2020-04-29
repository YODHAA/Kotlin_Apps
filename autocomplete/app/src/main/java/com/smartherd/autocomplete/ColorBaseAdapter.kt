package com.smartherd.autocomplete

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class ColorBaseAdapter : BaseAdapter() {

    private val list = color()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val inflater =
            parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.custom_view, null)

        val tv = view.findViewById<TextView>(R.id.tv_name)
        val card = view.findViewById<CardView>(R.id.card_view)

        tv.text = list[position].first
        card.setCardBackgroundColor(list[position].second)


        card.setOnClickListener {
            // Show selected color in a toast message
            Toast.makeText(
                parent.context,
                "Clicked : ${list[position].first}", Toast.LENGTH_SHORT
            ).show()

            val activity = parent.context as Activity
            val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)

            viewGroup.setBackgroundColor(list[position].second)

        }
        return view
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }


    override fun getCount(): Int {
        return list.size
    }


    private fun color(): List<Pair<String, Int>> {
        return listOf(
            Pair("IndianRed", Color.parseColor("#CD5C5C")),
            Pair("LIGHTCORAL", Color.parseColor("#F08080")),
            Pair("SALMON", Color.parseColor("#FA8072")),
            Pair("DARKSALMON", Color.parseColor("#E9967A")),
            Pair("LIGHTSALMON", Color.parseColor("#FFA07A")),
            Pair("CRIMSON", Color.parseColor("#DC143C")),
            Pair("RED", Color.parseColor("#FF0000")),
            Pair("FIREBRICK", Color.parseColor("#B22222")),
            Pair("DARKRED", Color.parseColor("#8B0000")),

            Pair("PINK", Color.parseColor("#FFC0CB")),
            Pair("LIGHTPINK", Color.parseColor("#FFB6C1")),
            Pair("HOTPINK", Color.parseColor("#FF69B4")),
            Pair("DEEPPINK", Color.parseColor("#FF1493")),
            Pair("MEDIUMVIOLETRED", Color.parseColor("#C71585")),
            Pair("PALEVIOLETRED", Color.parseColor("#DB7093"))
        )
    }

}