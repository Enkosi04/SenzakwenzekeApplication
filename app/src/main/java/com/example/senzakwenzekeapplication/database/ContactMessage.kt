package com.example.senzakwenzekeapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_messages")
data class ContactMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val message: String,
    val date: String
)