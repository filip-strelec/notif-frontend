package com.example.notifapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testButton: Button = findViewById(R.id.buttonTest) as Button
        testButton.setOnClickListener {
            val intent = Intent( this, dashboard::class.java)
            startActivity(intent)
        }

        val logIn: Button = findViewById(R.id.logIn) as Button


    }
}