package com.example.senzakwenzekeapplication

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Settings : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var tilName: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var appleHeader: LinearLayout
    private lateinit var circleInitials: TextView
    private lateinit var tvAppleName: TextView
    private lateinit var tvAppleEmail: TextView
    private lateinit var btnEditProfile: MaterialButton
    private lateinit var btnSaveAccount: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("SenzakwenzekePrefs", Context.MODE_PRIVATE)
        val isDarkMode = prefs.getBoolean("darkMode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val dbHelper = DatabaseHelper(this)
        val userEmail = prefs.getString("userEmail", "enkosimdingi5@gmail.com")

        // Bind Views
        appleHeader = findViewById(R.id.appleHeader)
        circleInitials = findViewById(R.id.circleInitials)
        tvAppleName = findViewById(R.id.tvAppleName)
        tvAppleEmail = findViewById(R.id.tvAppleEmail)
        btnEditProfile = findViewById(R.id.btnEditProfile)

        etName = findViewById(R.id.etChangeName)
        etPhone = findViewById(R.id.etChangePhone)
        tilName = findViewById(R.id.tilChangeName)
        tilPhone = findViewById(R.id.tilChangePhone)
        btnSaveAccount = findViewById(R.id.btnSaveAccount)

        val switchDarkMode = findViewById<SwitchMaterial>(R.id.switchDarkMode)
        switchDarkMode.isChecked = isDarkMode

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("darkMode", isChecked).apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // Initial Setup - Load saved data
        val savedName = prefs.getString("userName", "")
        val savedPhone = prefs.getString("userPhone", "")
        
        if (!savedName.isNullOrEmpty()) {
            etName.setText(savedName)
            etPhone.setText(savedPhone)
            showAppleHeader(savedName, userEmail ?: "")
            lockFields(true)
        } else {
            appleHeader.visibility = View.GONE
            lockFields(false)
        }

        btnSaveAccount.setOnClickListener {
            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            // Reset errors
            tilName.error = null
            tilPhone.error = null

            if (name.isEmpty()) {
                tilName.error = "Full Name is required"
                Toast.makeText(this, "Full Name is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                tilPhone.error = "Phone number is required"
                Toast.makeText(this, "Phone number is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("full_name", name)
                put("phone", phone)
            }

            if (!userEmail.isNullOrEmpty()) {
                db.update("Users", values, "email = ?", arrayOf(userEmail))
                
                prefs.edit().apply {
                    putString("userName", name)
                    putString("userPhone", phone)
                    apply()
                }
                
                Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
                
                showAppleHeader(name, userEmail)
                lockFields(true)
            }
        }

        btnEditProfile.setOnClickListener {
            appleHeader.visibility = View.GONE
            lockFields(false)
            etName.requestFocus()
        }

        findViewById<MaterialButton>(R.id.btnLogout).setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout?")
                .setMessage("Clear session and logout?")
                .setPositiveButton("Logout") { _, _ ->
                    prefs.edit().clear().apply()
                    val intent = Intent(this, Login::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
        
        // Preference Buttons 3-7
        findViewById<MaterialButton>(R.id.btnPref3).setOnClickListener { showInfoDialog("Service Area", "Bothas Hill, Durban and surrounding areas.") }
        findViewById<MaterialButton>(R.id.btnPref4).setOnClickListener { showInfoDialog("Packages", "Basic (R180), Standard (R250), Premium (R320) per guest.") }
        findViewById<MaterialButton>(R.id.btnPref5).setOnClickListener { showInfoDialog("Currency", "All prices are in ZAR (R).") }
        findViewById<MaterialButton>(R.id.btnPref6).setOnClickListener { showInfoDialog("Profile", "Senzakwenzeke Elegant Decor & Catering.") }
        findViewById<MaterialButton>(R.id.btnPref7).setOnClickListener { showInfoDialog("Privacy", "Strictly POPIA compliant.") }
    }

    private fun showInfoDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this).setTitle(title).setMessage(message).setPositiveButton("Close", null).show()
    }

    private fun showAppleHeader(name: String, email: String) {
        appleHeader.visibility = View.VISIBLE
        tvAppleName.text = name
        tvAppleEmail.text = email
        
        val parts = name.split(" ").filter { it.isNotBlank() }
        var initials = ""
        if (parts.isNotEmpty()) initials += parts[0][0].uppercaseChar()
        if (parts.size > 1) initials += parts[parts.size - 1][0].uppercaseChar()
        if (initials.isEmpty() && name.isNotEmpty()) initials = name.take(1).uppercase()
        
        circleInitials.text = if (initials.isNotEmpty()) initials else "?"
    }

    private fun lockFields(lock: Boolean) {
        etName.isEnabled = !lock
        etPhone.isEnabled = !lock
        // Optional: reduce alpha to show locked state
        etName.alpha = if (lock) 0.6f else 1.0f
        etPhone.alpha = if (lock) 0.6f else 1.0f
        btnSaveAccount.visibility = if (lock) View.GONE else View.VISIBLE
    }
}
