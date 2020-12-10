package com.example.c2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Delivery : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        val mainbtn = findViewById<Button>(R.id.buttonMain)
        mainbtn.setOnClickListener {
            startActivity(Intent(this@Delivery,MainActivity::class.java))
        }

    }
}