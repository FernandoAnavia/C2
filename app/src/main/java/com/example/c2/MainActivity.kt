package com.example.c2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menubtn = findViewById<Button>(R.id.buttonMenu)
        menubtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,Menu::class.java))
        }

        val deliverybtn = findViewById<Button>(R.id.buttonDelivery)
        deliverybtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,Delivery::class.java))
        }

        val pickUpbtn = findViewById<Button>(R.id.buttonPickup)
        pickUpbtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,pickUp::class.java))
        }

        val mainbtn = findViewById<Button>(R.id.buttonMain)
        mainbtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,MainActivity::class.java))
        }

    }
}