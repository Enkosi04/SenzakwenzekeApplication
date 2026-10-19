package com.example.senzakwenzekeapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Services : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Dark Mode Support
        val prefs = getSharedPreferences("SenzakwenzekePrefs", Context.MODE_PRIVATE)
        if (prefs.getBoolean("darkMode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        // === TOP NAVIGATION ===
        // Note: These navigation elements are currently handled by MainActivity's unified nav bar.
        /*
        findViewById<TextView>(R.id.navHomeS).setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
        }
        findViewById<TextView>(R.id.navAboutS).setOnClickListener {
            startActivity(Intent(this, About::class.java))
        }
        findViewById<TextView>(R.id.navBookingS).setOnClickListener {
            startActivity(Intent(this, Booking::class.java))
        }
        findViewById<TextView>(R.id.navContactS).setOnClickListener {
            startActivity(Intent(this, Contact::class.java))
        }
        */

        // === BOOK CONSULTATION BUTTON ===
        findViewById<MaterialButton>(R.id.btnBookNow).setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Book Consultation - R150")
                .setMessage("Continue to Booking?")
                .setPositiveButton("Continue") { _, _ ->
                    startActivity(Intent(this, Booking::class.java))
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
