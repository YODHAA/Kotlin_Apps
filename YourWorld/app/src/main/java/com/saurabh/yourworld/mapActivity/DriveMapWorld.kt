package com.saurabh.yourworld.mapActivity

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.saurabh.yourworld.Model.MyPlaces
import com.saurabh.yourworld.R
import com.saurabh.yourworld.common.Common
import com.saurabh.yourworld.remote.IGoogleAPIService
import kotlinx.android.synthetic.main.activity_drive_map_world.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DriveMapWorld : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    private var latitude:Double=0.toDouble()
    private var longitude:Double=0.toDouble()

    private lateinit var mLastLocation: Location
    private var mMarker: Marker?=null

    //Location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    companion object{
        private const val MY_PERMISSION_CODE:Int=1000
    }

    //initailse the API class
     lateinit var mServices: IGoogleAPIService

    internal lateinit var currentPlaces: MyPlaces


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drive_map_world)

        mServices=Common.googleApiService

       // AndroidInjection.inject(this) // injection happens here
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

// Request runtime permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkLocationPermission()) {
                buildLocationRequest()
                buildLocationCallBack()

                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.myLooper()
                )
            }
        }else{
            buildLocationRequest()
            buildLocationCallBack()

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        }

        //set event for the bottom navigation view
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_hospital -> nearByPlace("hospital")
                R.id.action_market -> nearByPlace("Market")
                R.id.action_restaurant -> nearByPlace("restaurant")
                R.id.action_school -> nearByPlace("School")

            }
            true

        }

    }

    private fun getUrl(latitude: Double, longitude: Double, typePlace: String): String {

        val googlePlaceUrl = StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json")
        googlePlaceUrl.append("?location=-33.8670522,151.1957362")
        googlePlaceUrl.append("&raidus=10000") //10km
        googlePlaceUrl.append("&type=$typePlace")
        googlePlaceUrl.append("&key=AIzaSyD8d8ytmqnqHHcAaUT-gYiaWrJ9kmM83Bc")

        Log.d("URL_DEBUG",googlePlaceUrl.toString())
        return googlePlaceUrl.toString()

    }

    private fun nearByPlace(typePlace: String) {

        // clear all market on Map
        map.clear()

        //build URL Request based on Location
        val url=getUrl(latitude,longitude,typePlace)


        mServices.getNearbyPlaces(url)
            .enqueue(object : Callback<MyPlaces> {
                override fun onFailure(call: Call<MyPlaces>?, t: Throwable?) {
                    Toast.makeText(baseContext,""+t!!.message,Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<MyPlaces>?, response: Response<MyPlaces>?) {
                    currentPlaces= response!!.body()!!
                    if(response.isSuccessful){

                        // var latLng:LatLng ?=null
                        for (i in 0 until response.body()!!.results!!.size)
                        {
                            val markerOptions=MarkerOptions()
                            val googlePlace=response.body()!!.results!![i]
                            val lat=googlePlace.geometry!!.location!!.lat
                            val lng=googlePlace.geometry!!.location!!.lng
                            val placeName=googlePlace.name
                            val latLng=LatLng(lat,lng)

                            markerOptions.position(latLng)
                            markerOptions.title(placeName)
                            if(typePlace.equals("hospital"))
                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hospital))
                            else if(typePlace.equals("market"))
                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_market))
                            else if(typePlace.equals("restaurant"))
                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant))
                            else if(typePlace.equals("school"))
                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_school))
                            else
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

                            // Assign index for Market
                            markerOptions.snippet(i.toString())

                            //Add Marker to Map
                            map!!.addMarker(markerOptions)
                            map!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                            map!!.animateCamera(CameraUpdateFactory.zoomTo(15f))

                        }
                        // map!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
//                         map!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(latitude,longitude)))
//                          map!!.animateCamera(CameraUpdateFactory.zoomTo(15f))

                    }
                }

            })

    }

    private fun buildLocationCallBack() {

        locationCallback=object:LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                //get the location
                mLastLocation= p0!!.locations.get(p0.locations.size-1)

                if(mMarker!=null){

                    mMarker!!.remove()
                }

                latitude=mLastLocation.latitude
                longitude=mLastLocation.longitude

                latitude=-33.8670522
                longitude=151.1957362

                val latLng=LatLng(latitude,longitude)
                val markerOptions=MarkerOptions()
                    .position(latLng)
                    .title("Saurabh's Home Location")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                mMarker= map.addMarker(markerOptions)

                //move Camera
                map.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                map.animateCamera(CameraUpdateFactory.zoomTo(17f))
            }
            //override funcion ends
        }
    }

    private fun buildLocationRequest() {

        locationRequest= LocationRequest()
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval=5000
        locationRequest.fastestInterval=3000
        locationRequest.smallestDisplacement=10f
    }

    private fun checkLocationPermission() :Boolean {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION))
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSION_CODE
                )
            else

                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSION_CODE
                )
            return false
        }else{
            return true
        }
    }

    //override OnRequestPErmissionResult

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            MY_PERMISSION_CODE -> {
                if(grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                        if(checkLocationPermission()){
                            buildLocationRequest()
                            buildLocationCallBack()

                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                            fusedLocationProviderClient.requestLocationUpdates(
                                locationRequest,
                                locationCallback,
                                Looper.myLooper()
                            )
                            map.isMyLocationEnabled=true
                        }
                    }
                }
                else{
                    Toast.makeText(this,"Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        } // end of when
    }


    override fun onStop() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onStop()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // init Google Play Service
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) ==
                PackageManager.PERMISSION_GRANTED
            ) {

                map.isMyLocationEnabled=true
            }
        }else{
            map.isMyLocationEnabled=true
        }

        //Enable Zoom Location
        map.uiSettings.isZoomControlsEnabled=true

        // Make the event clicked  on Marker
        map!!.setOnMarkerClickListener {
            marker ->
            //when user select marker , just get the results of places assign to static variables
            Common.currentResult=currentPlaces!!.results!![Integer.parseInt(marker.snippet)]
            startActivity(Intent(this,ViewPlaces::class.java))
            true
        }


    } // end of " onMapReady() "


}


