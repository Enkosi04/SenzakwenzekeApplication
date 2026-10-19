//package com.example.senzakwenzeke
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.button.MaterialButton
//import com.google.android.material.dialog.MaterialAlertDialogBuilder
//
//class ServicesActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_services)
//
//        // === BOOK CONSULTATION BUTTON ===
//        findViewById<MaterialButton>(R.id.btnBookNow).setOnClickListener {
//            MaterialAlertDialogBuilder(this)
//                .setTitle("Book Consultation - R150")
//                .setMessage("Consultation Process:\n\n• R150 booking fee (deductable if you book)\n• 30min consultation at Bothas Hill OR video call\n• You get full quote + moodboard\n• Fee waived if you confirm booking\n\nPayment: PayShap / EFT\n\nContinue to Booking?")
//                .setPositiveButton("Continue to Booking") { _, _ ->
//                    startActivity(Intent(this, BookingActivity::class.java))
//                }
//                .setNegativeButton("Cancel", null)
//                .show()
//        }
//
//        // === OPTIONAL: MAKE SERVICE CARDS CLICKABLE FOR THIRD-YEAR UX ===
//        // If you want cards to show details when tapped, add IDs to your cards:
//        // Decor card -> R.id.cardDecor, Catering -> R.id.cardCatering, Planning -> R.id.cardPlanning
//        // Then uncomment this:
//
//        /*
//        findViewById<androidx.cardview.widget.CardView>(R.id.cardDecor)?.setOnClickListener {
//            MaterialAlertDialogBuilder(this)
//                .setTitle("Decor & Styling - From R1,500")
//                .setMessage("• Zulu & Modern Fusion\n• Lobola full stage & backdrop\n• Weddings, Birthdays, Corporate\n• Includes setup & breakdown")
//                .setPositiveButton("Book Now") { _, _ -> startActivity(Intent(this, BookingActivity::class.java)) }
//                .show()
//        }
//
//        findViewById<androidx.cardview.widget.CardView>(R.id.cardCatering)?.setOnClickListener {
//            MaterialAlertDialogBuilder(this)
//                .setTitle("Catering - From R180 pp")
//                .setMessage("Basic R180 pp - 3 courses\nStandard R250 pp - 4 courses + dessert\nPremium R320 pp - 5 courses + bar setup\n\n✓ Halal, Kosher, Traditional")
//                .setPositiveButton("Book Now") { _, _ -> startActivity(Intent(this, BookingActivity::class.java)) }
//                .show()
//        }
//        */
//    }
//} This file has been moved to the .database package to resolve conflicts.
// Please use com.example.senzakwenzekeapplication.database.Service instead.