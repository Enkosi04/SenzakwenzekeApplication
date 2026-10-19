package com.example.senzakwenzekeapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.mindrot.jbcrypt.BCrypt

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val dbHelper = DatabaseHelper(this)

        val etFullName = findViewById<TextInputEditText>(R.id.etFullName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPhone = findViewById<TextInputEditText>(R.id.etPhone)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        
        val tilFullName = findViewById<TextInputLayout>(R.id.tilFullName)
        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilPhone = findViewById<TextInputLayout>(R.id.tilPhone)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)

        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)
        val tvLoginLink = findViewById<TextView>(R.id.tvLoginLink)

        btnRegister.setOnClickListener {
            val name = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            // Reset errors
            tilFullName?.error = null
            tilEmail?.error = null
            tilPhone?.error = null
            tilPassword?.error = null

            // SPECIFIC VALIDATION
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
            if (pass.isEmpty()) {
                tilPassword?.error = "Password is required"
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
                etPassword.requestFocus()
                return@setOnClickListener
            }
            
            // Format validation
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail?.error = "Valid email required"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (phone.length != 10) {
                tilPhone?.error = "Phone must be 10 digits"
                etPhone.requestFocus()
                return@setOnClickListener
            }
            if (pass.length < 6) {
                tilPassword?.error = "Password min 6 chars"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            val hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt())

            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("full_name", name)
                put("email", email)
                put("phone", phone)
                put("password_encrypted", hashedPassword)
            }

            val newRowId = db.insert("Users", null, values)
            if (newRowId != -1L) {
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Login::class.java))
                finish()
            } else {
                Toast.makeText(this, "Registration Failed or Email exists", Toast.LENGTH_SHORT).show()
            }
        }

        tvLoginLink.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}
