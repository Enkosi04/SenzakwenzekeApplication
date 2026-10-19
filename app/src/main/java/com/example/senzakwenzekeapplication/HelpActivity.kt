package com.example.senzakwenzekeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        findViewById<MaterialButton>(R.id.btnHelpBack).setOnClickListener {
            finish()
        }
    }
}
