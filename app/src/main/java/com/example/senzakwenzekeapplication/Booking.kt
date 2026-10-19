package com.example.senzakwenzekeapplication

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class Booking : AppCompatActivity() {

    private var selectedService = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val dbHelper = DatabaseHelper(this)
        val prefs = getSharedPreferences("SenzakwenzekePrefs", Context.MODE_PRIVATE)
        val userEmail = prefs.getString("userEmail", "")

        val tilFullName = findViewById<TextInputLayout>(R.id.tilFullName)
        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilPhone = findViewById<TextInputLayout>(R.id.tilPhone)
        val tilDate = findViewById<TextInputLayout>(R.id.tilDate)
        val tilGuests = findViewById<TextInputLayout>(R.id.tilGuests)

        val etFullName = findViewById<TextInputEditText>(R.id.etFullName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPhone = findViewById<TextInputEditText>(R.id.etPhone)
        val etDate = findViewById<TextInputEditText>(R.id.etDate)
        val etGuests = findViewById<TextInputEditText>(R.id.etGuests)
        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmitBooking)

        val btnDecor = findViewById<MaterialButton>(R.id.btnDecor)
        val btnCatering = findViewById<MaterialButton>(R.id.btnCatering)
        val btnFull = findViewById<MaterialButton>(R.id.btnFull)

        // Pre-fill user data if available
        etFullName.setText(prefs.getString("userName", ""))
        etEmail.setText(userEmail)
        etPhone.setText(prefs.getString("userPhone", ""))

        // Task 4: Date Picker Implementation
        etDate.isFocusable = false
        etDate.isClickable = true
        etDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                etDate.setText(formattedDate)
                tilDate?.error = null
            }, year, month, day)
            datePickerDialog.show()
        }

        fun selectService(service: String, button: MaterialButton) {
            selectedService = service
            btnDecor.setBackgroundColor(Color.parseColor("#F8F9FF"))
            btnCatering.setBackgroundColor(Color.parseColor("#F8F9FF"))
            btnFull.setBackgroundColor(Color.parseColor("#F8F9FF"))
            button.setBackgroundColor(Color.parseColor("#C9982E"))
        }

        btnDecor.setOnClickListener { selectService("Decor", btnDecor) }
        btnCatering.setOnClickListener { selectService("Catering", btnCatering) }
        btnFull.setOnClickListener { selectService("Full Event", btnFull) }

        btnSubmit.setOnClickListener {
            val name = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val date = etDate.text.toString().trim()
            val guests = etGuests.text.toString().trim()

            // Reset errors
            tilFullName?.error = null
            tilEmail?.error = null
            tilPhone?.error = null
            tilDate?.error = null
            tilGuests?.error = null

            // Task 1: Specific Validation
            if (name.isEmpty()) {
                tilFullName?.error = "Full Name is required"
                Toast.makeText(this, "Full Name is required", Toast.LENGTH_SHORT).show()
                etFullName.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                tilEmail?.error = "Email is required"
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                tilPhone?.error = "Phone number is required"
                Toast.makeText(this, "Phone number is required", Toast.LENGTH_SHORT).show()
                etPhone.requestFocus()
                return@setOnClickListener
            }
            if (date.isEmpty()) {
                tilDate?.error = "Event Date is required"
                Toast.makeText(this, "Event Date is required", Toast.LENGTH_SHORT).show()
                etDate.requestFocus()
                return@setOnClickListener
            }
            if (selectedService.isEmpty()) {
                Toast.makeText(this, "Please select a Service Type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (guests.isEmpty()) {
                tilGuests?.error = "Number of Guests is required"
                Toast.makeText(this, "Number of Guests is required", Toast.LENGTH_SHORT).show()
                etGuests.requestFocus()
                return@setOnClickListener
            }

            // Get user_id from email
            val db = dbHelper.writableDatabase
            val cursor = db.rawQuery("SELECT user_id FROM Users WHERE email = ?", arrayOf(userEmail))
            var userId = -1
            if (cursor.moveToFirst()) {
                userId = cursor.getInt(0)
            }
            cursor.close()

            if (userId != -1) {
                val values = ContentValues().apply {
                    put("user_id", userId)
                    put("service_id", if (selectedService == "Decor") 1 else if (selectedService == "Catering") 2 else 3)
                    put("event_date", date)
                    put("guests", guests)
                    put("location", "Bothas Hill")
                    put("status", "Pending")
                }
                db.insert("Bookings", null, values)
                Toast.makeText(this, "Booking Successful", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
