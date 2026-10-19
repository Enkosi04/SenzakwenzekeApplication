package com.example.senzakwenzekeapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Dark Mode Support
        val prefs = getSharedPreferences("SenzakwenzekePrefs", Context.MODE_PRIVATE)
        if (prefs.getBoolean("darkMode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // === VIEW OUR SERVICES BUTTON ===
        findViewById<MaterialButton>(R.id.btnViewServices).setOnClickListener {
            startActivity(Intent(this, Services::class.java))
        }
    }
}
