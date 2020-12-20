package com.example.c2

//16336 Elshy Xiomara Rosado Jimenez
//20344 Jose Fernando Gonzalez Anavia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val menubtn = findViewById<ImageButton>(R.id.buttonMenu)
        menubtn.setOnClickListener {
            startActivity(Intent(this,Menu::class.java))
        }

        val deliverybtn = findViewById<ImageButton>(R.id.buttonDelivery)
        deliverybtn.setOnClickListener {
            startActivity(Intent(this,Delivery::class.java))
        }

        val pickUpbtn = findViewById<ImageButton>(R.id.buttonPickup)
        pickUpbtn.setOnClickListener {
            startActivity(Intent(this,pickUp::class.java))
        }

        val mainbtn = findViewById<ImageButton>(R.id.buttonMain)
        mainbtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }


    }


}