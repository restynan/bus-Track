package com.example.buslocationtracker

import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import java.io.IOException
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.ArrayList

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var  locationListener:LocationListener
    private lateinit var  locationManager:LocationManager
    private  val Min_Time:Long=1000
    private  val Min_Dist:Float=5f
    private lateinit var latLng:LatLng
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Requesting User Permission ************************************8

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),PackageManager.PERMISSION_GRANTED)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),PackageManager.PERMISSION_GRANTED)

        latLng = LatLng(-34.0, 151.0)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//implementing Location Listner *****************************************************
        locationListener= object: LocationListener{
            override fun onLocationChanged(location: Location?) {
                // Add a marker to over new location****************************************
                try {
                    latLng = LatLng(location!!.latitude, location!!.longitude)
                    mMap.addMarker(MarkerOptions().position(latLng).title("My new postion"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                }
                catch (e:SecurityException){
                    e.printStackTrace()
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }

        }
        //Location Manager********************************************************************

       locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Min_Time, Min_Dist, locationListener
            )
        }
        catch (e:SecurityException){
            e.printStackTrace()
        }
    }




   fun getLocationdetails(view: View) {
       var latitude=latLng.latitude
        var longitude =latLng.longitude

        if(!(editText2.text.toString().isEmpty()||editText3.text.toString().isEmpty())){
            latitude=java.lang.Double.parseDouble(editText2.text.toString())
            longitude =java.lang.Double.parseDouble(editText3.text.toString())
            latLng= LatLng(latitude,longitude)
        }

        lateinit var geocoder : Geocoder
        var addresses = mutableListOf<Address>()


        geocoder= Geocoder(this, Locale.getDefault())
      lateinit  var address:String
        lateinit  var city:String
        lateinit  var state:String
        lateinit  var country:String
        lateinit  var postaladdress:String
        lateinit  var knonNames:String
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1)

            address = addresses.get(0).getAddressLine(0)
            city = addresses.get(0).locality
            state = addresses.get(0).adminArea
            country = addresses.get(0).countryName
           // postaladdress = addresses.get(0).postalCode
           // knonNames = addresses.get(0).featureName
        }
        catch (e:IOException){
            e.printStackTrace()

        }
        mMap.addMarker(MarkerOptions().position(latLng).title("maker"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        textView.setText("")
    }




}
