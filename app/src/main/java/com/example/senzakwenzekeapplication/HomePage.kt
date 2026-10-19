package com.example.senzakwenzekeapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // === TOP NAV ===
        // Note: These navigation elements are currently handled by MainActivity's unified nav bar.
        /*
        findViewById<TextView>(R.id.navHome).setOnClickListener {
            // Already home
        }
        findViewById<TextView>(R.id.navAbout).setOnClickListener {
            startActivity(Intent(this, About::class.java))
        }
        findViewById<TextView>(R.id.navServices).setOnClickListener {
            startActivity(Intent(this, Services::class.java))
        }
        findViewById<TextView>(R.id.navBooking).setOnClickListener {
            startActivity(Intent(this, Booking::class.java))
        }
        findViewById<TextView>(R.id.navContact).setOnClickListener {
            startActivity(Intent(this, Contact::class.java))
        }
        */

        // === HERO BUTTONS ===
        findViewById<MaterialButton>(R.id.btnBookNow).setOnClickListener {
            startActivity(Intent(this, Booking::class.java))
        }

        findViewById<MaterialButton>(R.id.btnExplore).setOnClickListener {
            startActivity(Intent(this, Services::class.java))
        }

        // === SERVICE CARDS ===
        // These IDs (cardDecor, cardCatering, cardPlanning) were not found in activity_home_page.xml
        /*
        findViewById<CardView>(R.id.cardDecor).setOnClickListener {
            startActivity(Intent(this, Services::class.java))
        }

        findViewById<CardView>(R.id.cardCatering).setOnClickListener {
            startActivity(Intent(this, Services::class.java))
        }

        findViewById<CardView>(R.id.cardPlanning).setOnClickListener {
            startActivity(Intent(this, Services::class.java))
        }
        */

        // === BOTTOM CTA ===
        findViewById<MaterialButton>(R.id.btnGoBooking).setOnClickListener {
            startActivity(Intent(this, Booking::class.java))
        }
    }
}
