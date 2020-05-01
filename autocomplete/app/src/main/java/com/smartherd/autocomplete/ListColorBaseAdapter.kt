package com.smartherd.autocomplete

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class ListColorBaseAdapter : BaseAdapter() {

    private val list = colors()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        var convertView = convertView

        if (convertView == null) {
            // If convert view is null then inflate a custom view and use it as convert view
            convertView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.list_custom_view, parent, false)

            // Create a new view holder instance using convert view
            viewHolder = ViewHolder(convertView)

            // Set the view holder as convert view tag
            convertView.tag = viewHolder
        } else {
            /*
                If convert view is not null then
                initialize the view holder using convert view tag.
            */
            viewHolder = convertView.tag as ViewHolder
        }

        // Display the current color name and value on view holder
        viewHolder.colorName.text = list[position].first
        viewHolder.imageView.setImageBitmap(drawCircle(list[position].second))

        // Set a click listener for card view
        viewHolder.card.setOnClickListener {
            // Show selected color in a toast message
            Toast.makeText(
                parent?.context,
                "Clicked : ${list[position].first}", Toast.LENGTH_SHORT
            ).show()

            // Get the activity reference from parent
            val activity = parent?.context as Activity

            // Get the activity root view
            val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content)
                .getChildAt(0)

            // Change the root layout background color
            viewGroup.setBackgroundColor(list[position].second)
        }

        // Finally, return the convert view
        return convertView!!
    }


    override fun getItem(position: Int): Any? {
        return list[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    private fun colors(): List<Pair<String, Int>> {
        return listOf(
            Pair("INDIANRED", Color.parseColor("#CD5C5C")),
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
            Pair("PALEVIOLETRED", Color.parseColor("#DB7093")),

            Pair("LIGHTSALMON", Color.parseColor("#FFA07A")),
            Pair("CORAL", Color.parseColor("#FF7F50")),
            Pair("TOMATO", Color.parseColor("#FF6347")),
            Pair("ORANGERED", Color.parseColor("#FF4500")),
            Pair("DARKORANGE", Color.parseColor("#FF8C00")),
            Pair("ORANGE", Color.parseColor("#FFA500")),

            Pair("GOLD", Color.parseColor("#FFD700")),
            Pair("YELLOW", Color.parseColor("#FFFF00")),
            Pair("LIGHTYELLOW", Color.parseColor("#FFFFE0")),
            Pair("LEMONCHIFFON", Color.parseColor("#FFFACD")),
            Pair("LIGHTGOLDENRODYELLOW", Color.parseColor("#FAFAD2")),
            Pair("PAPAYAWHIP", Color.parseColor("#FFEFD5")),
            Pair("MOCCASIN", Color.parseColor("#FFE4B5")),
            Pair("PEACHPUFF", Color.parseColor("#FFDAB9")),
            Pair("PALEGOLDENROD", Color.parseColor("#EEE8AA")),
            Pair("KHAKI", Color.parseColor("#F0E68C")),
            Pair("DARKKHAKI", Color.parseColor("#BDB76B"))
        )

    }

    private fun drawCircle(color: Int = Color.WHITE, width: Int = 200): Bitmap {
        // Initialize a new Bitmap object
        val bitmap: Bitmap = Bitmap.createBitmap(
            width, // Width
            width, // Height
            Bitmap.Config.ARGB_8888 // Config
        )

        // Initialize a new Canvas instance
        val canvas: Canvas = Canvas(bitmap)

        // Draw a solid color to the canvas background
        canvas.drawColor(Color.TRANSPARENT)

        // Initialize a new Paint instance to draw the Circle
        val paint: Paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = color
        paint.isAntiAlias = true

        // Calculate the available radius of canvas
        val radius: Int = Math.min(canvas.width, canvas.height / 2)

        // Set a pixels value to padding around the circle
        val padding: Int = 5

        // Finally, draw the circle on the canvas
        canvas.drawCircle(
            (canvas.width / 2).toFloat(), // cx
            (canvas.height / 2).toFloat(), // cy
            (radius - padding).toFloat(), // Radius
            paint // Paint
        )

        // Return the newly created bitmap
        return bitmap
    }
}


private class ViewHolder(view: View) {
    var colorName: TextView = view.findViewById(R.id.color_name)
    var imageView: ImageView = view.findViewById(R.id.image_view)
    val card: CardView = view.findViewById(R.id.card_view)

}

