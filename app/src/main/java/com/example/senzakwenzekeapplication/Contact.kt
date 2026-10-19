package com.example.senzakwenzekeapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.senzakwenzekeapplication.database.AppDatabase
import com.example.senzakwenzekeapplication.database.ContactMessage
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class Contact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        
        // Fixed: The ID in activity_contact.xml is btnSendMessage, not btnSend
        val btnSend = findViewById<Button>(R.id.btnSendMessage)

        val db = AppDatabase.getDatabase(this)

        btnSend?.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val msg = etMessage.text.toString().trim()

            if(name.isEmpty() || email.isEmpty() || msg.isEmpty()){
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Invalid email"
                return@setOnClickListener
            }

            val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            val contactMsg = ContactMessage(name=name, email=email, message=msg, date=date)

            lifecycleScope.launch {
                db.appDao().insertContactMessage(contactMsg)
                Toast.makeText(this@Contact, "Message saved! Thank you $name", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
