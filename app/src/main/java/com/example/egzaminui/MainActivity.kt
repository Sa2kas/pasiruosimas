package com.example.egzaminui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.egzaminui.person.PersonActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 : Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, PersonActivity::class.java)
            startActivity(intent)
        }

    }
}