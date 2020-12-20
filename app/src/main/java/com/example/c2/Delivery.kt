package com.example.c2

//16336 Elshy Xiomara Rosado Jimenez
//20344 Jose Fernando Gonzalez Anavia

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delivery.*

class Delivery : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        val mainbtn = findViewById<ImageButton>(R.id.buttonMain)
        mainbtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        btnSendO.setOnClickListener {
            saveData()
        }

    }

    //Saving data and shared preferences

        private fun saveData () {

            val insertedName = txtName.text.toString()
            svdTxtName.text = insertedName

            val insertedAddress = txtAddress.text.toString()
            svdTxtAddress.text = insertedAddress

            val insertedPhone = txtPhone.text.toString()
            svdTxtPhone.text = insertedPhone

            val insertedCommts = txtCommts.text.toString()
            svdTxtComm.text = insertedCommts

            val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPref",Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply {
                putString("NAME_KEY",insertedName)
                putString("ADDRESS_KEY",insertedAddress)
                putString("PHONE_KEY",insertedPhone)
                putString("COMMTS_KEY",insertedCommts)
                putBoolean("BOOLEAN_KEY", switch1.isChecked)

            }.apply()

        Toast.makeText(this,"Preferences saved",Toast.LENGTH_SHORT).show()

        }


        }
