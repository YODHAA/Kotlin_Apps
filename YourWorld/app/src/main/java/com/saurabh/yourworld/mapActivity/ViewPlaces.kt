package com.saurabh.yourworld.mapActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.yourworld.Model.PlaceDetail
import com.saurabh.yourworld.R
import com.saurabh.yourworld.common.Common
import com.saurabh.yourworld.remote.IGoogleAPIService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_view_places.*
import retrofit2.Call
import retrofit2.Response

class ViewPlaces : AppCompatActivity() {

    internal lateinit  var mService:IGoogleAPIService
    var mPlace:PlaceDetail?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_places)

        //Init Services
        mService=Common.googleApiService

        place_name.text=""
        place_address.text=""
        place_open_hour.text=""

        btn_show_map.setOnClickListener {
            val mapIntent=Intent(Intent.ACTION_VIEW, Uri.parse(mPlace!!.result!!.url))
            startActivity(mapIntent)
        }

        if(Common.currentResult!!.photos !=null && Common.currentResult!!.photos!!.size > 0)
        {
          Picasso.with(this)
              .load(getPhotoOfPlace(Common.currentResult!!.photos!![0].photo_reference!!,1000))
              .into(photo)
        }

        //Load Rating
        if(Common.currentResult!!.rating!=null)
        {
            rating_bar.rating=Common.currentResult!!.rating.toFloat()

        }else
        {
            rating_bar.visibility=View.GONE
        }

        //Load Open Hours
        if(Common.currentResult!!.openingHours !=null){
            place_open_hour.text="Open Now : "+ Common.currentResult!!.openingHours!!.open_now
        }
        else{
            place_open_hour.visibility=View.GONE
        }

        // Use Service to fetch Address and Name
        mService.getDetailPlace(getPlaceDetailUrl(Common.currentResult!!.place_id!!))
            .enqueue(object:retrofit2.Callback<PlaceDetail>{
                override fun onFailure(call: Call<PlaceDetail>, t: Throwable) {
                     Toast.makeText(baseContext,""+ t!!.message,Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<PlaceDetail>, response: Response<PlaceDetail>) {

                    mPlace=response!!.body()
                    place_address.text=mPlace!!.result!!.formatted_address
                    place_name.text=mPlace!!.result!!.name
                }

            })

    }

    private fun getPlaceDetailUrl(placeId: String): String {
         val url=StringBuilder("https://maps.googleapis.com/maps/api/place/details/json")
        url.append("?place_id=$placeId")
        url.append("&key=AIzaSyD8d8ytmqnqHHcAaUT-gYiaWrJ9kmM83Bc")
        return url.toString()
    }

    private fun getPhotoOfPlace(photoReference: String, maxWidth: Int): String {
        val url=StringBuilder("https://maps.googleapis.com/maps/api/place/details/json")
        url.append("?place_id=$maxWidth")
        url.append("&photoreference=$photoReference")
        url.append("&key=AIzaSyD8d8ytmqnqHHcAaUT-gYiaWrJ9kmM83Bc")
        return url.toString()
    }
}
