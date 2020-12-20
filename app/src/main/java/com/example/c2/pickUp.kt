package com.example.c2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class pickUp : AppCompatActivity() {
    //@SuppressLint("WrongViewCast")

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_up)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it

            val location1 = LatLng(53.36, -6.26)
            googleMap.addMarker(MarkerOptions().position(location1).title("Dorset College"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,10f))
        })



        val mainbtn = findViewById<ImageButton>(R.id.buttonMain)
        mainbtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}