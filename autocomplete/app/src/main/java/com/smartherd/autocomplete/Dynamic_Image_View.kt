package com.smartherd.autocomplete

import android.graphics.*
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.dynamic_images.*
import java.io.IOException
import java.net.URL

class Dynamic_Image_View : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dynamic_images)

        //  1. Create a new bitmap and display it on image view
        image_view_bitmap.setImageBitmap(drawCircle(Color.RED, Color.YELLOW, 700, 400))

        // 2. Display an image on image view from drawable
        image_view_drawable.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext, // Context
                R.drawable.gradient_rectangle // Drawable
            )
        )

        // 3. Display an image on image view from resource
        image_view_resource.setImageResource(R.drawable.login)

        //  4. Display an image into image view from assets folder
        val assetsBitmap: Bitmap? = getBitmapFromAssets("login.png")
        image_view_assets.setImageBitmap(assetsBitmap)


        // 5.  Display an image to image view from url
        DownLoadImageTask(image_view_url)
            .execute("https://www.gstatic.com/android/market_images/web/play_prism_hlock_2x.png")

    }

    // Custom method to get assets folder image as bitmap
    private fun getBitmapFromAssets(fileName: String): Bitmap? {
        return try {
            BitmapFactory.decodeStream(assets.open(fileName))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    // Method to draw a circle on a canvas and generate bitmap
    private fun drawCircle(
        bgColor: Int = Color.TRANSPARENT,
        circleColor: Int = Color.WHITE, width: Int = 200, height: Int = 200
    ): Bitmap {
        // Initialize a new Bitmap object
        val bitmap: Bitmap = Bitmap.createBitmap(
            width, // Width
            height, // Height
            Bitmap.Config.ARGB_8888 // Config
        )

        // Initialize a new Canvas instance
        val canvas: Canvas = Canvas(bitmap)

        // Draw a solid color to the canvas background
        canvas.drawColor(bgColor)

        // Initialize a new Paint instance to draw the Circle
        val paint: Paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = circleColor
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


// Class to download an image from url and display it into an image view
private class DownLoadImageTask(internal val imageView: ImageView) :
    AsyncTask<String, Void, Bitmap?>() {
    override fun doInBackground(vararg urls: String): Bitmap? {
        val urlOfImage = urls[0]
        return try {
            val inputStream = URL(urlOfImage).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) { // Catch the download exception
            e.printStackTrace()
            null
        }
    }

    override fun onPostExecute(result: Bitmap?) {
        if (result != null) {
            // Display the downloaded image into image view
            Toast.makeText(imageView.context, "download success", Toast.LENGTH_SHORT).show()
            imageView.setImageBitmap(result)
        } else {
            Toast.makeText(imageView.context, "Error downloading", Toast.LENGTH_SHORT).show()
        }
    }


}