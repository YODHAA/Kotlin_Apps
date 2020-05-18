package com.saurabh.yourworld.mapActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.common.api.Status

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.saurabh.yourworld.R

import kotlinx.android.synthetic.main.activity_home_maps.*
import java.util.*

class HomeMapsActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap
    private lateinit var placesClient:PlacesClient

    var placeFields= Arrays.asList(Place.Field.ID,
        Place.Field.NAME,
        Place.Field.ADDRESS)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_maps)

        initplaces()
        setupPlacesAutocomplete()

    }

    private fun setupPlacesAutocomplete() {

        val autocompleteFragment =supportFragmentManager
            .findFragmentById(R.id.fragment_place) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(placeFields)
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener{
            override fun onPlaceSelected(p0: Place) {
               Toast.makeText(this@HomeMapsActivity,""+p0.address,Toast.LENGTH_LONG).show()
            }

            override fun onError(p0: Status) {
                Toast.makeText(this@HomeMapsActivity,""+p0.statusMessage,Toast.LENGTH_LONG).show()
            }

        })


    }

    private fun initplaces() {
        Places.initialize(this,getString(R.string.places_api))
        placesClient=Places.createClient(this)
    }
}

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)


//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//    }

